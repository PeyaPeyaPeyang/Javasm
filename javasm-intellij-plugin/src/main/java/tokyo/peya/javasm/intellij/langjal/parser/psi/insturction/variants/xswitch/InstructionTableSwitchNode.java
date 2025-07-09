package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;

public class InstructionTableSwitchNode extends InstructionNode
{
    public InstructionTableSwitchNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public InstructionTableSwitchArgumentNode getTableSwitchArgument()
    {
        InstructionTableSwitchArgumentNode argumentNode = PsiTreeUtil.findChildOfType(this, InstructionTableSwitchArgumentNode.class);
        if (argumentNode == null)
            throw new IllegalStateException("Table switch instruction must have an argument node.");
        return argumentNode;
    }

    public int getLowIndex()
    {
        return this.getTableSwitchArgument().getLowIndex().intValue();
    }

    public LabelNameNode getDefaultBranchLabelName()
    {
        return this.getTableSwitchArgument().getDefaultBranchLabelName();
    }

    public LabelNameNode[] getBranchLabels()
    {
        return this.getTableSwitchArgument().getBranchLabels();
    }
}
