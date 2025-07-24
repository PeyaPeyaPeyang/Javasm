package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;

public class InstructionTableSwitchNode extends InstructionNode
{
    public InstructionTableSwitchNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @Nullable
    public InstructionTableSwitchArgumentNode getTableSwitchArgument()
    {
        return PsiTreeUtil.findChildOfType(
                this,
                InstructionTableSwitchArgumentNode.class
        );
    }

    public int getLowIndex()
    {
        InstructionTableSwitchArgumentNode arg =  this.getTableSwitchArgument();
        if (arg == null)
            return 0;
        Number lowIndex = arg.getLowIndex();
        if (lowIndex == null)
            return 0; // Default low index if not specified
        return lowIndex.intValue();
    }

    @Nullable
    public LabelNameNode getDefaultBranchLabelName()
    {
        InstructionTableSwitchArgumentNode arg =  this.getTableSwitchArgument();
        if (arg == null)
            return null;
        return arg.getDefaultBranchLabelName();
    }

    @Nullable
    public LabelNameNode[] getBranchLabels()
    {
        InstructionTableSwitchArgumentNode arg =  this.getTableSwitchArgument();
        if (arg == null)
            return null;
        return arg.getBranchLabels();
    }

    @Override
    public int getInstructionSize()
    {
        InstructionTableSwitchArgumentNode argumentNode = this.getTableSwitchArgument();
        if  (argumentNode == null)
            throw new IllegalStateException();
        LabelNameNode branches = argumentNode.getDefaultBranchLabelName();
        if (branches == null)
            throw new IllegalStateException();
        LabelNameNode[] branchLabels = argumentNode.getBranchLabels();
        if (branchLabels == null)
            throw new IllegalStateException();

        Number lowIndexValue = argumentNode.getLowIndex();
        if (lowIndexValue == null)
            throw new IllegalStateException();
        int lowIndex = lowIndexValue.intValue();
        return calcActualSize(lowIndex, branchLabels);
    }

    private int calcActualSize(int lowIndex, LabelNameNode[] branchLabels)
    {
        int highIndex = lowIndex + branchLabels.length - 1;
        int nPairs = highIndex - lowIndex + 1;

        int baseOffset = this.getStartInstructionOffset(); // assume you have this
        int padding = (4 - (baseOffset + 1) % 4) % 4;

        int size = 1;              // opcode
        size += padding;           // padding to 4-byte alignment
        size += 4;                 // default offset
        size += 4;                 // low index
        size += 4;                 // high index
        size += 4 * nPairs;       // branch labels
        return size;
    }
}
