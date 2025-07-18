package tokyo.peya.javasm.intellij.langjal;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassDefinitionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.InstructionSetNode;

public class JALFile extends PsiFileBase
{
    public JALFile(@NotNull FileViewProvider viewProvider)
    {
        super(viewProvider, JALLanguage.INSTANCE);
    }

    @Nullable
    public ClassDefinitionNode getClassDefinition()
    {
        return PsiTreeUtil.findChildOfType(this, ClassDefinitionNode.class);
    }

    @Override
    public @NotNull FileType getFileType()
    {
        return JALFileType.INSTANCE;
    }

    @Override
    public String toString()
    {
        return "Java Assembly Language File";
    }

    public static JALFile getJALFile(@NotNull Project project, @NotNull VirtualFile file)
    {
        PsiFile psiFile = ApplicationManager.getApplication().runReadAction((Computable<? extends PsiFile>) () ->
                PsiManager.getInstance(project).findFile(file)
        );

        return psiFile instanceof JALFile ? (JALFile) psiFile: null;
    }

    public @Nullable PsiElement findInstructionRelatedElement(int line)
    {
        Document doc = this.getViewProvider().getDocument();
        if (doc == null || line < 0 || line >= doc.getLineCount())
            return null;

        int offset = doc.getLineStartOffset(line);
        PsiElement element = this.findElementAt(offset);
        if (element == null)
            return null;

        // element は InstructionNode または InstructionSetNode のどちらか。
        while (element != null && !(element instanceof InstructionNode || element instanceof InstructionSetNode))
        {
            if (element instanceof ANTLRPsiNode)
                element = element.getFirstChild();
            else
                element = element.getNextSibling();
        }

        if (element == null)
            return null;

        int elementLine = doc.getLineNumber(element.getTextRange().getStartOffset());
        return elementLine == line ? element: null;
    }
}
