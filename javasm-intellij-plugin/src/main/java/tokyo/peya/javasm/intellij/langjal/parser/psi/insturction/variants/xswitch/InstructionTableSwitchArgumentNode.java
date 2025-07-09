package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.NumberNode;

public class InstructionTableSwitchArgumentNode extends ANTLRPsiNode
{
    public InstructionTableSwitchArgumentNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public LabelNameNode getDefaultBranchLabelName()
    {
        return (LabelNameNode) getFirstChild();
    }

    @NotNull
    public Number getLowIndex()
    {
        NumberNode lowValueNode = PsiTreeUtil.findChildOfType(this, NumberNode.class);
        if (lowValueNode == null)
            throw new IllegalStateException("Low value node is missing in " + this.getText());

        return lowValueNode.toNumber();
    }

    @NotNull
    public LabelNameNode[] getBranchLabels()
    {
        LabelNameNode[] branchLabelsIncludeDefault = this.findChildrenByClass(LabelNameNode.class);
        if (branchLabelsIncludeDefault.length < 1)
            throw new IllegalStateException("Branch labels are missing in " + this.getText());
        LabelNameNode[] branchLabels = new LabelNameNode[branchLabelsIncludeDefault.length - 1];
        System.arraycopy(branchLabelsIncludeDefault, 1, branchLabels, 0, branchLabels.length);

        return branchLabels;
    }
}
