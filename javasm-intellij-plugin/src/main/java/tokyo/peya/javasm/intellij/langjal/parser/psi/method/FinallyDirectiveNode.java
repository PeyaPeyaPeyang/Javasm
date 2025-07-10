package tokyo.peya.javasm.intellij.langjal.parser.psi.method;

import com.intellij.lang.ASTNode;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNode;

public class FinallyDirectiveNode extends ANTLRPsiNode
{
    public FinallyDirectiveNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public LabelNode getFinallyBlockLabel()
    {
        LabelNode label = this.findChildByClass(LabelNode.class);
        if (label == null)
            throw new IllegalStateException("Finally directive must have a label");

        return label;
    }
}
