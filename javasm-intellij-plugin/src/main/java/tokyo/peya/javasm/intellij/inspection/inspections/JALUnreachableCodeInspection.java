package tokyo.peya.javasm.intellij.inspection.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.inspection.AbstractJALInspection;
import tokyo.peya.javasm.intellij.inspection.JALPsiElementVisitor;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;

public class JALUnreachableCodeInspection extends AbstractJALInspection
{
    public JALUnreachableCodeInspection()
    {
        super("JALUnreachableCode");
    }

    @Override
    protected @NotNull JALPsiElementVisitor buildJALVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly,
                                                            @NotNull LocalInspectionToolSession session)
    {
        return new JALPsiElementVisitor()
        {
            @Override
            protected void visitInstruction(@NotNull InstructionNode instruction)
            {
                checkInstruction(instruction, holder);
            }
        };
    }

    private static void checkInstruction(@NotNull InstructionNode instruction, @NotNull ProblemsHolder holder)
    {
        PsiElement parent = instruction.getParent().getParent();
        if (parent == null)
            return;

        PsiElement[] siblings = parent.getChildren();

        // 自分の直前までの兄弟ノードを後ろから前に向かって見ていく
        boolean foundMe = false;
        for (int i = siblings.length - 1; i >= 0; i--)
        {
            PsiElement prev = siblings[i];
            if (prev.getFirstChild() == instruction)
            {
                foundMe = true;
                continue;
            }
            else if (!foundMe) // 自分より後ろのノードは無視
                continue;

            // ラベルがあればジャンプされてくる可能性があるので到達可能
            if (prev instanceof LabelNode)
                return;

            if (prev instanceof ANTLRPsiNode psiNode &&
                    psiNode.getFirstChild() instanceof InstructionNode prevInstruction)
            {
                if (isJumpInstruction(prevInstruction))
                {
                    // ジャンプ命令の直後なので到達不能
                    holder.registerProblem(
                            instruction,
                            "This instruction is unreachable " +
                                    "(after " + prevInstruction.getInstructionName() + " instruction)",
                            ProblemHighlightType.WARNING
                    );
                    return;
                }
            }
        }
    }

    private static boolean isJumpInstruction(@NotNull InstructionNode instruction)
    {
        String instructionName = instruction.getInstructionName();
        return "goto".equals(instructionName)
                || "return".equals(instructionName)
                || "ireturn".equals(instructionName)
                || "lreturn".equals(instructionName)
                || "freturn".equals(instructionName)
                || "dreturn".equals(instructionName)
                || "areturn".equals(instructionName)
                || "athrow".equals(instructionName)
                || "tableswitch".equals(instructionName)
                || "lookupswitch".equals(instructionName);
    }
}
