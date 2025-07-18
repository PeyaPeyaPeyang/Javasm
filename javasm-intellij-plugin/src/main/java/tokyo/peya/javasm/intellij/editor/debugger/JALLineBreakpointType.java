package tokyo.peya.javasm.intellij.editor.debugger;

import com.intellij.debugger.ui.breakpoints.Breakpoint;
import com.intellij.debugger.ui.breakpoints.JavaLineBreakpointTypeBase;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.xdebugger.breakpoints.XBreakpoint;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.java.debugger.breakpoints.properties.JavaLineBreakpointProperties;
import tokyo.peya.javasm.intellij.langjal.JALFile;
import tokyo.peya.javasm.intellij.langjal.JALFileType;

public class JALLineBreakpointType extends JavaLineBreakpointTypeBase<JavaLineBreakpointProperties>
{
    public static final String ID = "JAL_LINE_BREAKPOINT";
    public static final String TITLE = "JAL Line Breakpoint";

    public JALLineBreakpointType()
    {
        super(ID, TITLE);
    }

    @Override
    public boolean canPutAt(@NotNull VirtualFile file, int line, @NotNull Project project)
    {
        if (file.getFileType() != JALFileType.INSTANCE)
            return false;

        if (line < 0)
            return false;

        JALFile jalFile = JALFile.getJALFile(project, file);
        if (jalFile == null)
            return false;

        PsiElement element = jalFile.findInstructionRelatedElement(line);
        return element != null;
    }

    @Override
    public @Nullable JavaLineBreakpointProperties createBreakpointProperties(@NotNull VirtualFile file, int line)
    {
        return new JavaLineBreakpointProperties();
    }

    @Override
    public @NotNull Breakpoint<JavaLineBreakpointProperties> createJavaBreakpoint(Project project,
                                                                                  XBreakpoint<JavaLineBreakpointProperties> xBreakpoint)
    {
        return new JALLineBreakpoint(project, xBreakpoint);
    }
}
