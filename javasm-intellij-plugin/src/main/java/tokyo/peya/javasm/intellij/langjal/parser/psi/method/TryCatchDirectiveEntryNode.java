package tokyo.peya.javasm.intellij.langjal.parser.psi.method;

import com.intellij.lang.ASTNode;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNode;

public class TryCatchDirectiveEntryNode extends ANTLRPsiNode
{
    public TryCatchDirectiveEntryNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public LabelNode getTryEndLabel()
    {
        LabelNode label = this.findChildByClass(LabelNode.class);
        if (label == null)
            throw new IllegalStateException("Try catch entry must have a try end label");

        return label;
    }

    @Nullable
    public CatchDirectiveNode getCatchDirective()
    {
        return this.findChildByClass(CatchDirectiveNode.class);
    }

    @Nullable
    public FinallyDirectiveNode getFinallyDirective()
    {
        FinallyDirectiveNode finallyDirectiveNode = this.findChildByClass(FinallyDirectiveNode.class);
        if (finallyDirectiveNode == null)
        {
            CatchDirectiveNode catchDirectiveNode = this.getCatchDirective();
            if (catchDirectiveNode != null)
                finallyDirectiveNode = catchDirectiveNode.getFinallyDirective();
        }

        return finallyDirectiveNode;
    }
}
