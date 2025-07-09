package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.TypeDescriptorNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;

public class InstructionTypeArgumentNode extends InstructionNode
{
    public InstructionTypeArgumentNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public TypeDescriptorNode getType()
    {
        TypeDescriptorNode typeDescriptorNode = PsiTreeUtil.findChildOfType(this, TypeDescriptorNode.class);
        if (typeDescriptorNode == null)
            throw new IllegalStateException("TypeDescriptorNode is not found in TypeArgumentInstructionNode");
        return typeDescriptorNode;
    }

    @Override
    public String toString()
    {
        return "InstructionNode(" + this.getInstructionName() + " type=" + this.getType().getText() + ")";
    }
}
