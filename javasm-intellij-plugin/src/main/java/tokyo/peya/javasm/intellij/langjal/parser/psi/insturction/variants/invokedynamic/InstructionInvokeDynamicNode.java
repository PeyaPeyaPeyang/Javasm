package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.invokedynamic;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDescriptorNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodNameNode;
import tokyo.peya.javasm.langjal.compiler.jvm.MethodDescriptor;

public class InstructionInvokeDynamicNode extends InstructionNode
{
    public InstructionInvokeDynamicNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @Nullable
    public String getMethodName()
    {
        MethodNameNode methodNameNode = PsiTreeUtil.findChildOfType(this, MethodNameNode.class);
        if (methodNameNode == null)
            return null;

        return methodNameNode.getMethodName();
    }

    @Nullable
    public MethodDescriptor getMethodDescriptor()
    {
        MethodDescriptorNode methodDescriptorNode = PsiTreeUtil.findChildOfType(this, MethodDescriptorNode.class);
        if (methodDescriptorNode == null)
            return null;

        return methodDescriptorNode.getMethodDescriptor();
    }

    @Nullable
    public InvokeDynamicMethodHandleNode getBootstrapMethodHandle()
    {
        return PsiTreeUtil.findChildOfType(
                this,
                InvokeDynamicMethodHandleNode.class
        );
    }

    @NotNull
    public InvokeDynamicArgumentNode[] getBootstrapMethodArguments()
    {
        return this.findChildrenByClass(InvokeDynamicArgumentNode.class);
    }
}
