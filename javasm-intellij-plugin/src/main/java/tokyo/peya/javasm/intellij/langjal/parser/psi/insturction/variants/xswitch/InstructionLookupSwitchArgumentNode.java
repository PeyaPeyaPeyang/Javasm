package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch;

import com.intellij.lang.ASTNode;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;

public class InstructionLookupSwitchArgumentNode extends ANTLRPsiNode
{
    public InstructionLookupSwitchArgumentNode(@NotNull ASTNode node)
    {
        super(node);
    }

    public InstructionLookupSwitchCaseNode[] getBranches()
    {
        return this.findChildrenByClass(InstructionLookupSwitchCaseNode.class);
    }

    public InstructionLookupSwitchCaseNode getDefault()
    {
        InstructionLookupSwitchCaseNode[] cases = this.getBranches();
        for (InstructionLookupSwitchCaseNode c : cases)
            if (c.isDefaultCase())
                return c;

        throw new IllegalStateException("No default case found in lookup switch instruction");
    }
}
