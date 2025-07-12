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

public class JALPushBigIntegerInspection extends AbstractJALInspection
{
    public JALPushBigIntegerInspection()
    {
        super("JALPushBigInteger");
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
        catch (Exception e)
        {
            holder.registerProblem(
                    argument,
                    "Invalid number format: " + argument.getText(),
                    ProblemHighlightType.ERROR
            );
            return;
        }

        double value = number.doubleValue();
        if (instructionName.equals("bipush") && (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE))
        {
            // 値が -128 から 127 の範囲外なら，かつそれが -32,768 から 32,767 の範囲内ならば，sipush を使うように促す
            if (Short.MIN_VALUE <= value && value <= Short.MAX_VALUE)
            {
                holder.registerProblem(
                        node,
                        "bipush can only push values in the range of -128 to 127. " +
                                "Use 'sipush " + argument.getText() + "' instead.",
                        ProblemHighlightType.GENERIC_ERROR_OR_WARNING,
                        new JALReplaceInstructionQuickFix("sipush " + argument.getText(), node)
                );
            }
        }
        // 値が -32,768 から 32,767 の範囲外ならば, ldc X を使うように促す
        if (value < Short.MIN_VALUE || value > Short.MAX_VALUE)
        {
            holder.registerProblem(
                    node,
                    "bipush/sipush can only push values in the range of -32,768 to 32,767. " +
                            "Use 'ldc " + argument.getText() + "' instead.",
                    ProblemHighlightType.GENERIC_ERROR_OR_WARNING,
                    new JALReplaceInstructionQuickFix("ldc " + argument.getText(), node)
            );
        }
    }
}
