package tokyo.peya.javasm.intellij.inspection.quickfixes;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.SmartPointerManager;
import com.intellij.psi.SmartPsiElementPointer;
import org.jetbrains.annotations.NotNull;

public class JALReplaceInstructionQuickFix implements LocalQuickFix
{
    private final String replacementInstruction;
    @SafeFieldForPreview
    private final SmartPsiElementPointer<PsiElement> target;

    public JALReplaceInstructionQuickFix(@NotNull String replacementInstruction, @NotNull PsiElement target)
    {
        this.replacementInstruction = replacementInstruction;
        this.target = SmartPointerManager.createPointer(target);
    }

    @Override
    public @IntentionFamilyName @NotNull String getFamilyName()
    {
        return "Replace with '" + this.replacementInstruction + "'";
    }

    @Override
    public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor)
    {
        PsiElement targetElement = this.target.getElement();
        if (targetElement == null)
            return; // なにもしない

        TextRange textRange = targetElement.getTextRange();
        Document document = targetElement.getContainingFile().getViewProvider().getDocument();
        if (document == null)
            throw new IllegalStateException("Document is null for the instruction node");

        document.replaceString(textRange.getStartOffset(), textRange.getEndOffset(), this.replacementInstruction);
    }
}
