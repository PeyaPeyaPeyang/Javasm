package tokyo.peya.javasm.intellij.langjal.parser.psi.clazz;

import com.intellij.lang.ASTNode;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDefinitionNode;

public class ClassBodyItemNode extends ANTLRPsiNode
{
    public ClassBodyItemNode(@NotNull ASTNode node)
    {
        super(node);
    }

    public boolean isMethod()
    {
        return this.findChildByClass(MethodDefinitionNode.class) != null;
    }

    @Nullable
    public MethodDefinitionNode getMethod()
    {
        return this.findChildByClass(MethodDefinitionNode.class);
    }
}
