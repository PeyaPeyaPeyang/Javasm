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

    @Override
    public int getInstructionSize()
    {
        // wide した場合はサイズが ２倍になる
        int multiplier = this.isWidened() ? 2: 1;
        return super.getInstructionSize() * multiplier;
    }
}
