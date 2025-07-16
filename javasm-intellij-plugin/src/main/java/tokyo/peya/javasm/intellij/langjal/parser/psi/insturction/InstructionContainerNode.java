package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InstructionContainerNode extends ANTLRPsiNode
{
    public InstructionContainerNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @Nullable
    public InstructionNode getInstruction()
    {
        InstructionNode instructionNode = PsiTreeUtil.findChildOfType(this, InstructionNode.class);
        if (instructionNode == null)
            throw new IllegalStateException("InstructionContainerNode must contain an InstructionNode");
        return instructionNode;
    }

    @Override
    public String toString()
    {
        return "InstructionContainer(" + this.getInstruction().getInstructionName() + ")";
    }
}
