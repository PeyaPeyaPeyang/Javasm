package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.invokedynamic;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.jvm.MethodDescriptor;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDescriptorNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodNameNode;

public class InstructionInvokeDynamicNode extends InstructionNode
{
    public InstructionInvokeDynamicNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public String getMethodName()
    {
        MethodNameNode methodNameNode = PsiTreeUtil.findChildOfType(this, MethodNameNode.class);
        if (methodNameNode == null)
            throw new IllegalStateException("InstructionInvokeDynamic must have a MethodNameNode child");

        return methodNameNode.getMethodName();
    }

    @NotNull
    public MethodDescriptor getMethodDescriptor()
    {
        MethodDescriptorNode methodDescriptorNode = PsiTreeUtil.findChildOfType(this, MethodDescriptorNode.class);
        if (methodDescriptorNode == null)
            throw new IllegalStateException("InstructionInvokeDynamic must have a MethodDescriptorNode child");

        return methodDescriptorNode.getMethodDescriptor();
    }

    @NotNull
    public InvokeDynamicMethodHandleNode getBootstrapMethodHandle()
    {
        InvokeDynamicMethodHandleNode bootstrapMethodHandleNode = PsiTreeUtil.findChildOfType(this, InvokeDynamicMethodHandleNode.class);
        if (bootstrapMethodHandleNode == null)
            throw new IllegalStateException("InstructionInvokeDynamic must have a InvokeDynamicMethodHandleNode child");

        return bootstrapMethodHandleNode;
    }

    @NotNull
    public InvokeDynamicArgumentNode[] getBootstrapMethodArguments()
    {
        InvokeDynamicArgumentNode[] arguments = this.findChildrenByClass(InvokeDynamicArgumentNode.class);
        if (arguments.length == 0)
            throw new IllegalStateException("InstructionInvokeDynamic must have at least one InvokeDynamicArgumentNode child");

        return arguments;
    }
}
