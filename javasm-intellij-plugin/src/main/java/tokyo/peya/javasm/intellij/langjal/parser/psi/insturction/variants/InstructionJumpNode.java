package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;

public class InstructionJumpNode extends InstructionNode
{
    public InstructionJumpNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public LabelNameNode getJumpLabel()
    {
        LabelNameNode labelNode = PsiTreeUtil.findChildOfType(this, LabelNameNode.class);
        if (labelNode == null)
            throw new IllegalStateException("LabelNode is not found in JumpArgumentInstructionNode");
        return labelNode;
    }

    @Override
    public String toString()
    {
        return "InstructionNode(" + this.getInstructionName() + " jump=" + this.getJumpLabel().getText() + ")";
    }
}
