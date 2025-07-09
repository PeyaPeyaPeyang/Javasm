package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.LocalReferenceNode;

public class InstructionLocalAccessNode extends InstructionWidenableNode
{
    public InstructionLocalAccessNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public String getReferenceName()
    {
        LocalReferenceNode reference = PsiTreeUtil.findChildOfType(this, LocalReferenceNode.class);
        if (reference == null)
            throw new IllegalStateException("Local's reference not found in " + this.getText());

        return reference.getText();
    }

    @Override
    public String toString()
    {
        String wide = this.isWidened() ? " wide " : "";
        return "InstructionNode(" + wide + this.getInstructionName() + ", argREF=" + this.getReferenceName() + ")";
    }
}
