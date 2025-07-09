package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.BooleanNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.NumberNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.StringNode;

public class JVMScalarNode extends ANTLRPsiNode
{
    public JVMScalarNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public PsiElement getScalarNode()
    {
        PsiElement node = PsiTreeUtil.findChildOfType(this, StringNode.class);
        if (node == null)
            node = PsiTreeUtil.findChildOfType(this, NumberNode.class);
        if (node == null)
            node = PsiTreeUtil.findChildOfType(this, BooleanNode.class);
        if (node == null)
            throw new IllegalStateException("No scalar node found in " + this.getClass().getName());

        return node;
    }

    @NotNull
    public Object getScalarValue()
    {
        PsiElement scalarNode = this.getScalarNode();
        return switch (scalarNode)
        {
            case StringNode stringNode -> stringNode.toStringValue();
            case NumberNode numberNode -> numberNode.toNumber();
            case BooleanNode booleanNode -> booleanNode.toBoolean();
            default -> throw new IllegalStateException("Unknown scalar type: " + scalarNode.getClass().getName());
        };
    }
}
