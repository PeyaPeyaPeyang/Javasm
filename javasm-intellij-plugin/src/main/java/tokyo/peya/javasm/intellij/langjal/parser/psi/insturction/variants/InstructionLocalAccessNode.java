package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class InstructionLocalAccessNode extends InstructionWidenableNode
{
    public InstructionLocalAccessNode(@NotNull ASTNode node)
    {
        super(node);
    }


    @Override
    public String toString()
    {
        String wide = this.isWidened() ? " wide ": "";
        return "InstructionNode(" + wide + this.getInstructionName() + ", argREF=" + this.getReferenceName() + ")";
    }
}
