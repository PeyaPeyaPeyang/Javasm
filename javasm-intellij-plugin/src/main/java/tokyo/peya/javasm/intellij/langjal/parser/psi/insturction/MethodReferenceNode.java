package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.FullQualifiedNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDescriptorNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodNameNode;
import tokyo.peya.langjal.compiler.jvm.MethodDescriptor;
import tokyo.peya.langjal.compiler.jvm.TypeDescriptor;

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

    @Nullable
    public TypeDescriptor getMethodOwnerDescriptor()
    {
        String ownerName = null;
        FullQualifiedNameNode owner = this.getMethodOwner();
        if (owner == null)
        {
            IdentifierNode idNode = PsiTreeUtil.findChildOfType(this, IdentifierNode.class);
            if (idNode != null)
                ownerName = idNode.getText();
        }
        else
            ownerName = owner.getText();

        if (ownerName == null || ownerName.isEmpty())
            return null;
        else
            return TypeDescriptor.className(ownerName);
    }

    @Nullable
    public String getMethodName()
    {
        MethodNameNode methodNameNode = PsiTreeUtil.findChildOfType(this, MethodNameNode.class);
        if (methodNameNode == null)
            return null;

        return methodNameNode.getMethodName();
    }

    @NotNull
    public IdentifierNode getMethodNameNode()
    {
        MethodNameNode methodNameNode = PsiTreeUtil.findChildOfType(this, MethodNameNode.class);
        if (methodNameNode == null)
            throw new IllegalStateException("Method name node is not found in instruction method reference node");

        IdentifierNode identifierNode = PsiTreeUtil.findChildOfType(methodNameNode, IdentifierNode.class);
        if (identifierNode == null)
            throw new IllegalStateException("Identifier node is not found in MethodNameNode");

        return identifierNode;
    }

    @Nullable
    public MethodDescriptor getMethodDescriptor()
    {
        MethodDescriptorNode methodDescriptorNode = PsiTreeUtil.findChildOfType(this, MethodDescriptorNode.class);
        if (methodDescriptorNode == null)
            return null;

        try {
            return methodDescriptorNode.getMethodDescriptor();
        } catch (IllegalArgumentException e) {
            return null; // メソッド記述子の解析に失敗した場合は null を返す
        }
    }

    @Override
    public String toString()
    {
        String owner = this.getMethodOwnerDescriptor() == null ? "": this.getMethodOwnerDescriptor() + "->";
        return "MethodReference(" + owner + "this." + this.getMethodName() + this.getMethodDescriptor() + ")";
    }

    public boolean isSpecialMethod()
    {
        return false;
    }
}
