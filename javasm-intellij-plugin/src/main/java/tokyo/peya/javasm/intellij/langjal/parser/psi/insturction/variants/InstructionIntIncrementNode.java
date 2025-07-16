package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.NumberNode;

public class InstructionIntIncrementNode extends InstructionWidenableNode
{
    public InstructionIntIncrementNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @Nullable
    public Number getIncrementValue()
    {
        NumberNode numberNode = PsiTreeUtil.findChildOfType(this, NumberNode.class);
        if (numberNode == null)
            return null; // No increment value provided
        return numberNode.toNumber();
    }
}
