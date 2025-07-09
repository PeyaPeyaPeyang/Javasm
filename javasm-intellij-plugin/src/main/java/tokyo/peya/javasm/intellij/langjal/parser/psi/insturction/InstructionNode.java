package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction;

import com.intellij.lang.ASTNode;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;

public class InstructionNode extends ANTLRPsiNode
{
    public InstructionNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public String getInstructionName()
    {
        InstructionNameNode instructionNameNode = this.findChildByClass(InstructionNameNode.class);
        if (instructionNameNode == null)
            return this.getText();

        return instructionNameNode.getInstructionName();
    }
}
