package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;

public class InstructionWidenableNode extends InstructionNode
{
    public InstructionWidenableNode(@NotNull ASTNode node)
    {
        super(node);
    }

    public boolean isWidened()
    {
        return PsiTreeUtil.findChildOfType(this, InstructionWideNode.class) != null;
    }
}
