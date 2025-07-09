package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction;

import com.intellij.lang.ASTNode;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;

public class InstructionContainerNode extends ANTLRPsiNode
{
    public InstructionContainerNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public InstructionNode getInstruction()
    {
        InstructionNode instructionNode = this.findChildByClass(InstructionNode.class);
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
