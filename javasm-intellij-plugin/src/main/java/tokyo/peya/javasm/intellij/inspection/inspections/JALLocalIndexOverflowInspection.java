package tokyo.peya.javasm.intellij.inspection.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.inspection.AbstractJALInspection;
import tokyo.peya.javasm.intellij.inspection.JALPsiElementVisitor;
import tokyo.peya.javasm.intellij.inspection.quickfixes.JALReplaceInstructionQuickFix;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.LocalReferenceNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.InstructionWideNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.InstructionWidenableNode;
import tokyo.peya.javasm.langjal.compiler.utils.EvaluatorCommons;

public class JALLocalIndexOverflowInspection extends AbstractJALInspection
{
    public JALLocalIndexOverflowInspection()
    {
        super("JALLocalIndexOverflow");
    }

    @Override
    protected @NotNull JALPsiElementVisitor buildJALVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly,
                                                            @NotNull LocalInspectionToolSession session)
    {
        return new JALPsiElementVisitor()
        {
            @Override
            protected void visitInstruction(@NotNull InstructionNode node)
            {
                checkInstruction(holder, node);
            }
        };
    }

    private static void checkInstruction(@NotNull ProblemsHolder holder, @NotNull InstructionNode node)
    {
        if (!(node instanceof InstructionWidenableNode widenable))
            return;

        LocalReferenceNode localReference = widenable.getLocalReference();
        if (!localReference.isNumberSpecifier()) // 番号指定のみチェック
            return;

        PsiElement nameIdentifier = localReference.getNameIdentifier();
        assert nameIdentifier != null; // #isNumberSpecifier() でチェック済み
        String localIndexText = nameIdentifier.getText();
        Number localIndex;
        try
        {
            localIndex = EvaluatorCommons.toNumber(localIndexText);
            assert localIndex != null;
        }
        catch (IllegalArgumentException e)
        {
            holder.registerProblem(
                    nameIdentifier,
                    "Invalid local index: " + localIndexText,
                    ProblemHighlightType.GENERIC_ERROR
            );
            return;
        }

        int localIndexValue = localIndex.intValue();
        if (localIndexValue < 0)
        {
            holder.registerProblem(
                    nameIdentifier,
                    "Local index cannot be negative: " + localIndexText,
                    ProblemHighlightType.GENERIC_ERROR
            );
            return;
        }

        boolean isWidened = widenable.isWidened();
        if (localIndexValue > 0xFFFF)
        {
            holder.registerProblem(
                    nameIdentifier,
                    "Local index overflow: " + localIndexText +
                            "(max: 0xFFFF(65535) for widened instructions)",
                    ProblemHighlightType.GENERIC_ERROR
            );
            return;
        }

        if (localIndexValue > 0xFF)
        {
            if (isWidened)
                return;
            String widenedInstruction = "wide " + node.getText();
            holder.registerProblem(
                    nameIdentifier,
                    "Local index overflow: " + localIndexText +
                            "(max: 0xFF(255) for non-widened instructions)",
                    ProblemHighlightType.GENERIC_ERROR,
                    new JALReplaceInstructionQuickFix(widenedInstruction, node)
            );
        }
        else
        {
            if (!isWidened)
                return;
            InstructionWideNode wideNode = widenable.getWideNode();
            assert wideNode != null; // isWidened() でチェック済み

            String deWidenedInstruction = node.getText().replaceFirst("wide ", "");
            TextRange wideRange = wideNode.getTextRange().shiftLeft(node.getTextRange().getStartOffset());


            // 過剰なワイド命令のチェック
            holder.registerProblem(
                    node,
                    "The widened instruction is unnecessary for local index: " + localIndexText +
                            " (max: 0xFF(255) for non-widened instructions)",
                    ProblemHighlightType.GENERIC_ERROR,
                    wideRange,
                    new JALReplaceInstructionQuickFix(deWidenedInstruction, node)
            );
        }
    }
}
