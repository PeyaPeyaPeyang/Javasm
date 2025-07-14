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
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class JALShortenableVariableOperationInspection extends AbstractJALInspection
{
    public JALShortenableVariableOperationInspection()
    {
        super("JALShortenableVariableOperation");
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
        int opcode = node.getOpcode();
        // EPpcodes.ILOAD ~ EOpcodes.ALOAD,  または EOpcodes.ISTORE ~ EOpcodes.ASTORE のいずれかでなければならない
        if (!((EOpcodes.ILOAD <= opcode && opcode <= EOpcodes.ALOAD) || (EOpcodes.ISTORE <= opcode && opcode <= EOpcodes.ASTORE)))
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
        int value = number.intValue();
        if (value > 3)
            return;

        // 変数のインデックスが 0 ~ 3 の範囲であれば，xload_n または xstore_n を使うように促す
        String newInstructionName = node.getInstructionName() + "_" + value;
        holder.registerProblem(
                node,
                "Variable operation with index " + value + " can be shortened to '" + newInstructionName + "'",
                ProblemHighlightType.WEAK_WARNING,
                new JALReplaceInstructionQuickFix(newInstructionName, node)
        );
    }
}
