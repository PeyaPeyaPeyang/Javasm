package tokyo.peya.javasm.intellij.langjal.parser.psi.clazz;

import com.intellij.lang.ASTNode;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;

public class ClassBodyNode extends ANTLRPsiNode
{
    public ClassBodyNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public ClassBodyItemNode[] getItems()
    {
        return this.findChildrenByClass(ClassBodyItemNode.class);
    }
}
