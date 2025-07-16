package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.JVMScalarNode;

public class InstructionScalarNode extends InstructionNode
{
    public InstructionScalarNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public JVMScalarNode getScalarNode()
    {
        JVMScalarNode scalarNode = PsiTreeUtil.findChildOfType(this, JVMScalarNode.class);
        if (scalarNode == null)
            throw new IllegalStateException("InstructionScalarNode must have a JVMScalarNode child");
        return scalarNode;
    }

    @Nullable
    public Object getScalarValue()
    {
        return this.getScalarNode().getScalarValue();
    }
}
