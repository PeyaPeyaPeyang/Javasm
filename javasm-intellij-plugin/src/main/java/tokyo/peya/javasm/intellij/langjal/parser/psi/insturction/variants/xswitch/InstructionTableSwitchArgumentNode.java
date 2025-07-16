package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.NumberNode;

public class InstructionTableSwitchArgumentNode extends ANTLRPsiNode
{
    public InstructionTableSwitchArgumentNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @Nullable
    public LabelNameNode getDefaultBranchLabelName()
    {
        PsiElement firstChild = this.getFirstChild();
        if (!(firstChild instanceof LabelNameNode))
            return null;
        return (LabelNameNode) firstChild;
    }

    @Nullable
    public Number getLowIndex()
    {
        NumberNode lowValueNode = PsiTreeUtil.findChildOfType(this, NumberNode.class);
        if (lowValueNode == null)
            return 0;

        return lowValueNode.toNumber();
    }

    @Nullable
    public LabelNameNode[] getBranchLabels()
    {
        LabelNameNode[] branchLabelsIncludeDefault = this.findChildrenByClass(LabelNameNode.class);
        if (branchLabelsIncludeDefault.length < 1)
            return null;
        LabelNameNode[] branchLabels = new LabelNameNode[branchLabelsIncludeDefault.length - 1];
        System.arraycopy(branchLabelsIncludeDefault, 1, branchLabels, 0, branchLabels.length);

        return branchLabels;
    }
}
