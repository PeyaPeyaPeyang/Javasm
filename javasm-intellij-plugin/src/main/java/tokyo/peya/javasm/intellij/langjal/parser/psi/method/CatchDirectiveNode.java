package tokyo.peya.javasm.intellij.langjal.parser.psi.method;

import com.intellij.lang.ASTNode;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.FullQualifiedNameNode;

public class CatchDirectiveNode extends ANTLRPsiNode
{
    public CatchDirectiveNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public FullQualifiedNameNode getExceptionType()
    {
        FullQualifiedNameNode exceptionTypeNode = this.findChildByClass(FullQualifiedNameNode.class);
        if (exceptionTypeNode == null)
            throw new IllegalStateException("Catch directive must have an exception type");

        return exceptionTypeNode;
    }

    @NotNull
    public LabelNode getCatchBlockLabel()
    {
        LabelNode labelNode = this.findChildByClass(LabelNode.class);
        if (labelNode == null)
            throw new IllegalStateException("Catch directive must have a label");

        return labelNode;
    }

    @Nullable
    public FinallyDirectiveNode getFinallyDirective()
    {
        return this.findChildByClass(FinallyDirectiveNode.class);
    }
}
