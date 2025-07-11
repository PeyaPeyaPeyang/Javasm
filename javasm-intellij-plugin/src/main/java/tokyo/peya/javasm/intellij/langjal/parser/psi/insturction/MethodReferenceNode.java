package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.FullQualifiedNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDescriptorNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodNameNode;
import tokyo.peya.javasm.langjal.compiler.jvm.MethodDescriptor;

public class MethodReferenceNode extends InstructionNode
{
    public MethodReferenceNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @Nullable
    public FullQualifiedNameNode getMethodOwner()
    {
        return PsiTreeUtil.findChildOfType(this, FullQualifiedNameNode.class);
    }

    @NotNull
    public String getMethodName()
    {
        MethodNameNode methodNameNode = PsiTreeUtil.findChildOfType(this, MethodNameNode.class);
        if (methodNameNode == null)
            throw new IllegalStateException("MethodReferenceNode must have a MethodNameNode child");

        return methodNameNode.getMethodName();
    }

    @NotNull
    public MethodDescriptor getMethodDescriptor()
    {
        MethodDescriptorNode methodDescriptorNode = PsiTreeUtil.findChildOfType(this, MethodDescriptorNode.class);
        if (methodDescriptorNode == null)
            throw new IllegalStateException("MethodReferenceNode must have a MethodDescriptorNode child");

        return methodDescriptorNode.getMethodDescriptor();
    }

    @Override
    public String toString()
    {
        String owner = this.getMethodOwner() == null ? "": this.getMethodOwner().getText() + "->";
        return "MethodReference(" + owner + "this." + this.getMethodName() + this.getMethodDescriptor() + ")";
    }

    public boolean isSpecialMethod()
    {
        return false;
    }
}
