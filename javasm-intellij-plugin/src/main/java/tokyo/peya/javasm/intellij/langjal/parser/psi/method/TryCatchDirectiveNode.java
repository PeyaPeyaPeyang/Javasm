package tokyo.peya.javasm.intellij.langjal.parser.psi.method;

import com.intellij.lang.ASTNode;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;

public class TryCatchDirectiveNode extends ANTLRPsiNode
{
    public TryCatchDirectiveNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public TryCatchDirectiveEntryNode[] getTryCatchEntries()
    {
        return this.findChildrenByClass(TryCatchDirectiveEntryNode.class);
    }
}
