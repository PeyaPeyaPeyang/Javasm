package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierMethodClInitNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierMethodInitNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierNode;

public class SpecialMethodReferenceNode extends MethodReferenceNode
{
    public SpecialMethodReferenceNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @Override
    public @NotNull String getMethodName()
    {
        IdentifierNode specialMethodNameNode = PsiTreeUtil.findChildOfType(this, IdentifierMethodClInitNode.class);
        if (specialMethodNameNode == null)
            specialMethodNameNode = PsiTreeUtil.findChildOfType(this, IdentifierMethodInitNode.class);

        if (specialMethodNameNode == null)
            throw new IllegalStateException("SpecialMethodReferenceNode must have a special method name child");

        return specialMethodNameNode.getText();
    }

    @Override
    public boolean isSpecialMethod()
    {
        return true; // <clinit> or <init> のどっちか
    }
}
