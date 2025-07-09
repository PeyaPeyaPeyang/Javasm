package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;

public class WidenableInstructionNode extends InstructionNode
{
    public WidenableInstructionNode(@NotNull ASTNode node)
    {
        super(node);
    }

    public boolean isWidened()
    {
        return this.findChildByClass(InstructionWideNode.class) != null;
    }
}
