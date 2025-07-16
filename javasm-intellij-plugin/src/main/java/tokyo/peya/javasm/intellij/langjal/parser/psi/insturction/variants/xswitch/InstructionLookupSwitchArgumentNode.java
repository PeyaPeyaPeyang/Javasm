package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch;

import com.intellij.lang.ASTNode;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InstructionLookupSwitchArgumentNode extends ANTLRPsiNode
{
    public InstructionLookupSwitchArgumentNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public InstructionLookupSwitchCaseNode[] getBranches()
    {
        return this.findChildrenByClass(InstructionLookupSwitchCaseNode.class);
    }

    @Nullable
    public InstructionLookupSwitchCaseNode getDefault()
    {
        InstructionLookupSwitchCaseNode[] cases = this.getBranches();
        for (InstructionLookupSwitchCaseNode c : cases)
            if (c.isDefaultCase())
                return c;

        return null;
    }
}
