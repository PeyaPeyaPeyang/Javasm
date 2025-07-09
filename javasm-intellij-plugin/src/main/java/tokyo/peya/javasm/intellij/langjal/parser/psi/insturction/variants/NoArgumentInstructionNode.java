package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;

public class NoArgumentInstructionNode extends InstructionNode
{
    public NoArgumentInstructionNode(@NotNull ASTNode node)
    {
        super(node);
    }
}
