package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.JALPSITreeUtils;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionLocalReferenceNode;

public class LocalReferenceArgumentInstructionNode extends WidenableInstructionNode
{
    public LocalReferenceArgumentInstructionNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public String getReferenceName()
    {
        InstructionLocalReferenceNode reference = this.findChildByClass(InstructionLocalReferenceNode.class);
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
