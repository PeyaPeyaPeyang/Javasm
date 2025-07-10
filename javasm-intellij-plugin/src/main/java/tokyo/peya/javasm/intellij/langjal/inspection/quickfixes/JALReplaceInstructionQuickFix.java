package tokyo.peya.javasm.intellij.langjal.inspection.quickfixes;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;

public class JALReplaceInstructionQuickFix implements LocalQuickFix
{
    private final String replacementInstruction;

    public JALReplaceInstructionQuickFix(@NotNull String replacementInstruction)
    {
        this.replacementInstruction = replacementInstruction;
    }

    @Override
    public @IntentionFamilyName @NotNull String getFamilyName()
    {
        return "Replace this instruction with '" + this.replacementInstruction + "'";
    }

    @Override
    public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor)
    {
        if (!(descriptor.getPsiElement() instanceof InstructionNode instructionNode))
            throw new IllegalArgumentException("Descriptor does not contain an InstructionNode");

        TextRange textRange = instructionNode.getTextRange();
        Document document = instructionNode.getContainingFile().getViewProvider().getDocument();
        if (document == null)
            throw new IllegalStateException("Document is null for the instruction node");

        document.replaceString(textRange.getStartOffset(), textRange.getEndOffset(), this.replacementInstruction);
    }
}
