package tokyo.peya.javasm.intellij.inspection.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.inspection.AbstractJALInspection;
import tokyo.peya.javasm.intellij.inspection.JALPsiElementVisitor;
import tokyo.peya.javasm.intellij.inspection.quickfixes.JALReplaceInstructionQuickFix;
import tokyo.peya.javasm.intellij.langjal.parser.psi.NumberNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;

public class JALPushIntegerInspection extends AbstractJALInspection
{
    public JALPushIntegerInspection()
    {
        super("JALPushInteger");
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
                checkInstruction(node, holder);
            }
        };
    }

    private static void checkInstruction(@NotNull InstructionNode node, @NotNull ProblemsHolder holder)
    {
        String instructionName = node.getInstructionName();
        if (!("bipush".equals(instructionName) || "sipush".equals(instructionName)))
            return;

        NumberNode argument = node.getInstructionArgument(NumberNode.class);
        if (argument == null)
            return;

        Number number;
        try
        {
            number = argument.toNumber();
        }
        catch (Exception ignored)
        {
            return;
        }
        long value = number.longValue();

        // 値が -1 なら, iconst_m1 を使うように促す
        if (value == -1)
        {
            holder.registerProblem(
                    node,
                    "Pushing -1 is discouraged, use 'iconst_m1' instead",
                    ProblemHighlightType.WEAK_WARNING,
                    new JALReplaceInstructionQuickFix("iconst_m1", node)
            );
        }
        // 値が 0~5 なら, iconst_X を使える。
        else if (0 < value && value <= 5)
        {
            String replacementInstruction = "iconst_" + value;
            holder.registerProblem(
                    node,
                    "Pushing " + value + " is discouraged, use '" + replacementInstruction + "' instead",
                    ProblemHighlightType.WEAK_WARNING,
                    new JALReplaceInstructionQuickFix(replacementInstruction, node)
            );
        }
        // sipush なのに， 範囲が -128 ~ 127 なら, bipush を使うように促す
        else if ("sipush".equals(instructionName) && (Byte.MIN_VALUE <= value && value <= Byte.MAX_VALUE))
        {
            holder.registerProblem(
                    node,
                    "Using 'sipush' with a value in the range of -128 to 127 is discouraged, use 'bipush' instead",
                    ProblemHighlightType.WEAK_WARNING,
                    new JALReplaceInstructionQuickFix("bipush " + value, node)
            );
        }
    }
}
