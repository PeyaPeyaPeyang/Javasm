package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.FieldReferenceNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;

public class InstructionFieldAccessNode extends InstructionNode
{
    public InstructionFieldAccessNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public FieldReferenceNode getFieldReference()
    {
        FieldReferenceNode fieldReferenceNode = PsiTreeUtil.findChildOfType(this, FieldReferenceNode.class);
        if (fieldReferenceNode == null)
            throw new IllegalStateException("FieldReferenceNode is not found in FieldReferenceArgumentInstructionNode");
        return fieldReferenceNode;
    }
}
