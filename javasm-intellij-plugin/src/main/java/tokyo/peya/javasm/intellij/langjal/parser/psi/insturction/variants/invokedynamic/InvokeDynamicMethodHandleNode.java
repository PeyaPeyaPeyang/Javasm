package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.invokedynamic;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.jvm.InvocationType;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.MethodReferenceNode;

public class InvokeDynamicMethodHandleNode extends ANTLRPsiNode
{
    public InvokeDynamicMethodHandleNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public InvocationType getHandleType()
    {
        InvokeDynamicMethodHandleTypeNode handleTypeNode = PsiTreeUtil.findChildOfType(
                this,
                InvokeDynamicMethodHandleTypeNode.class
        );
        if (handleTypeNode == null)
            throw new IllegalStateException("InvokeDynamicMethodHandleTypeNode not found in " + this.getText());

        return handleTypeNode.getMethodHandleType();
    }

    @NotNull
    public MethodReferenceNode getMethodReference()
    {
        MethodReferenceNode methodReferenceNode = PsiTreeUtil.findChildOfType(this, MethodReferenceNode.class);
        if (methodReferenceNode == null)
            throw new IllegalStateException("MethodReferenceNode not found in " + this.getText());

        return methodReferenceNode;
    }
}
