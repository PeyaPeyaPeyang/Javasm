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

    @NotNull
    public InvokeDynamicMethodHandleTypeNode getMethodHandleTypeArgument()
    {
        InvokeDynamicMethodHandleTypeNode methodHandleTypeNode = PsiTreeUtil.findChildOfType(
                this,
                InvokeDynamicMethodHandleTypeNode.class
        );
        if (methodHandleTypeNode == null)
            throw new IllegalStateException("InvokeDynamicMethodHandleTypeNode is not found in " + this.getText());

        return methodHandleTypeNode;
    }

    @NotNull
    public InvokeDynamicMethodHandleNode getMethodHandleArgument()
    {
        InvokeDynamicMethodHandleNode methodHandleNode = PsiTreeUtil.findChildOfType(
                this,
                InvokeDynamicMethodHandleNode.class
        );
        if (methodHandleNode == null)
            throw new IllegalStateException("InvokeDynamicMethodHandleNode is not found in " + this.getText());

        return methodHandleNode;
    }
}
