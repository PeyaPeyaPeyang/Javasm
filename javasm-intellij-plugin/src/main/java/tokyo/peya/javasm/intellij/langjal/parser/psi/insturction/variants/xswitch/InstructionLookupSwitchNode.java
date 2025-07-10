package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;

public class InstructionLookupSwitchNode extends InstructionNode
{
    public InstructionLookupSwitchNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public InstructionLookupSwitchArgumentNode getTableSwitchArgument()
    {
        InstructionLookupSwitchArgumentNode argumentNode = PsiTreeUtil.findChildOfType(
                this,
                InstructionLookupSwitchArgumentNode.class
        );
        if (argumentNode == null)
            throw new IllegalStateException("Lookup switch instruction must have an argument node.");
        return argumentNode;
    }

    public LabelNameNode getDefaultBranchLabelName()
    {
        return this.getTableSwitchArgument().getDefault().getBranchLabel();
    }

    public InstructionLookupSwitchCaseNode[] getCaseBranches()
    {
        return this.getTableSwitchArgument().getBranches();
    }
}
