package tokyo.peya.javasm.intellij.langjal.parser.psi.method;

import com.intellij.lang.ASTNode;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;

public class MethodBodyNode extends ANTLRPsiNode
{
    public MethodBodyNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public InstructionSetNode[] getInstructionSets()
    {
        return this.findChildrenByClass(InstructionSetNode.class);
    }
}
