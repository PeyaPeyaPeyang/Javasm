package tokyo.peya.javasm.intellij.langjal.parser.psi.clazz;

import com.intellij.lang.ASTNode;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierNode;

public class ClassNameNode extends ANTLRPsiNode
{
    public ClassNameNode(@NotNull ASTNode node)
    {
        super(node);
    }

    public IdentifierNode getClassName()
    {
        return (IdentifierNode) getFirstChild();
    }
}
