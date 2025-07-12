package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.LocalReferenceNode;

public class InstructionWidenableNode extends InstructionNode
{
    public InstructionWidenableNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public LocalReferenceNode getLocalReference()
    {
        LocalReferenceNode localReferenceNode = PsiTreeUtil.findChildOfType(this, LocalReferenceNode.class);
        if (localReferenceNode == null)
            throw new IllegalStateException("LocalReferenceNode is not found in WidenableInstructionNode");
        return localReferenceNode;
    }

    @NotNull
    public String getReferenceName()
    {
        LocalReferenceNode reference = PsiTreeUtil.findChildOfType(this, LocalReferenceNode.class);
        if (reference == null)
            throw new IllegalStateException("Local's reference not found in " + this.getText());

        return reference.getText();
    }

    public boolean isWidened()
    {
        return PsiTreeUtil.findChildOfType(this, InstructionWideNode.class) != null;
    }

    @Nullable
    public InstructionWideNode getWideNode()
    {
        return PsiTreeUtil.findChildOfType(this, InstructionWideNode.class);
    }

    @Override
    public int getInstructionSize()
    {
        // wide した場合はサイズが ２倍になる
        int multiplier = this.isWidened() ? 2: 1;
        return super.getInstructionSize() * multiplier;
    }
}
