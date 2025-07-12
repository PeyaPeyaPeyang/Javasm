package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.NumberNode;

public class InstructionIntIncrementNode extends InstructionWidenableNode
{
    public InstructionIntIncrementNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public Number getIncrementValue()
    {
        NumberNode numberNode = PsiTreeUtil.findChildOfType(this, NumberNode.class);
        if (numberNode == null)
            throw new IllegalStateException("NumberNode is not found in IntIncrementInstructionNode");
        return numberNode.toNumber();
    }
}
