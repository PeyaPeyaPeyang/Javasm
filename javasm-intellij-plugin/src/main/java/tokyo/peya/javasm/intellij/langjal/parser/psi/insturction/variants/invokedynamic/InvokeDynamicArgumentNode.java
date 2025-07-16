package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.invokedynamic;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.JVMScalarNode;

public class InvokeDynamicArgumentNode extends InstructionNode
{
    public InvokeDynamicArgumentNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @Nullable
    public JVMScalarNode getScalarArgument()
    {
        return PsiTreeUtil.findChildOfType(this, JVMScalarNode.class);
    }

    @Nullable InvokeDynamicMethodHandleTypeNode getMethodHandleTypeArgument()
    {
        return PsiTreeUtil.findChildOfType(
                this,
                InvokeDynamicMethodHandleTypeNode.class
        );
    }

    @Nullable
    public InvokeDynamicMethodHandleNode getMethodHandleArgument()
    {
        return PsiTreeUtil.findChildOfType(
                this,
                InvokeDynamicMethodHandleNode.class
        );
    }
}
