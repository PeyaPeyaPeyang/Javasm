package tokyo.peya.javasm.intellij.editor.debugger;

import com.intellij.debugger.NoDataException;
import com.intellij.debugger.PositionManager;
import com.intellij.debugger.SourcePosition;
import com.intellij.debugger.engine.DebugProcess;
import com.intellij.debugger.requests.ClassPrepareRequestor;
import com.intellij.debugger.requests.RequestManager;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.sun.jdi.AbsentInformationException;
import com.sun.jdi.Location;
import com.sun.jdi.ReferenceType;
import com.sun.jdi.request.ClassPrepareRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import tokyo.peya.javasm.intellij.langjal.JALFile;
import tokyo.peya.javasm.intellij.langjal.JALFileType;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassDefinitionNode;

import java.util.List;
import java.util.Set;

public class JALPositionManager implements PositionManager
{
    private final DebugProcess debug;

    public JALPositionManager(@NotNull DebugProcess debug)
    {
        this.debug = debug;
    }

    @Override
    public @Nullable Set<? extends FileType> getAcceptedFileTypes()
    {
        return Set.of(JALFileType.INSTANCE);
    }

    @Override
    public @Nullable SourcePosition getSourcePosition(@Nullable Location loc) throws NoDataException
    {
        if (loc == null)
            throw NoDataException.INSTANCE;

        try
        {
            int line = loc.lineNumber() - 1; // JVM は 1 ベースの行番号を使用するため、1 を減算
            String sourceName = loc.sourceName();

            JALFile jalFile = this.findJALFile(sourceName);
            Document doc = jalFile.getViewProvider().getDocument();
            if (doc == null)
                throw NoDataException.INSTANCE;
            int allLines = doc.getLineCount();
            if (line < 0 || line >= allLines)
                throw NoDataException.INSTANCE;

            return SourcePosition.createFromLine(jalFile, line);
        }
        catch (AbsentInformationException e)
        {
            throw NoDataException.INSTANCE;
        }
    }

    private VirtualFile findSourceFile(@NotNull String sourceName) throws NoDataException
    {
        VirtualFile found = ApplicationManager.getApplication().runReadAction((Computable<? extends VirtualFile>) () ->
                FilenameIndex.getVirtualFilesByName(
                        sourceName,
                        GlobalSearchScope.projectScope(this.debug.getProject())
                ).stream().findFirst().orElse(null)
        );
        if (found == null || !found.isValid())
            throw NoDataException.INSTANCE;

        return found;
    }

    private JALFile findJALFile(@NotNull String sourceName) throws NoDataException
    {
        VirtualFile file = this.findSourceFile(sourceName);
        JALFile jalFile = JALFile.getJALFile(this.debug.getProject(), file);
        if (jalFile == null)
            throw NoDataException.INSTANCE;
        return jalFile;
    }

    @Override
    public @NotNull @Unmodifiable List<ReferenceType> getAllClasses(@NotNull SourcePosition pos) throws NoDataException
    {
        String fileName = pos.getFile().getName();
        String className = getClassNameFromJALFile(fileName);

        return this.debug.getVirtualMachineProxy().classesByName(className);
    }

    private String getClassNameFromJALFile(@NotNull String fileName) throws NoDataException
    {
        if (!fileName.endsWith(".jal"))
            throw new IllegalArgumentException("File name must end with .jal: " + fileName);

        JALFile jalFile = this.findJALFile(fileName);
        String className = ApplicationManager.getApplication().runReadAction((Computable<String>) () -> {
            ClassDefinitionNode classDef = jalFile.getClassDefinition();
            if (classDef == null)
                return null;
            return classDef.getFullQualifiedClassName();
        });

        if (className == null || className.isEmpty())
            throw NoDataException.INSTANCE;
        return className;
    }

    @Override
    public @NotNull List<Location> locationsOfLine(@NotNull ReferenceType refType,
                                                   @NotNull SourcePosition pos) throws NoDataException
    {
        try
        {
            int line = pos.getLine() + 1; // JVM は 1 ベースの行番号を使用するため、1 を加算
            return refType.locationsOfLine(line);
        }
        catch (AbsentInformationException e)
        {
            throw NoDataException.INSTANCE;
        }
    }

    @Override
    public @Nullable ClassPrepareRequest createPrepareRequest(@NotNull ClassPrepareRequestor requester,
                                                              @NotNull SourcePosition pos) throws NoDataException
    {
        String className = getClassNameFromJALFile(pos.getFile().getName());
        RequestManager requestManager = this.debug.getRequestsManager();

        return requestManager.createClassPrepareRequest(requester, className);
    }
}
