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

    @NotNull
    public InstructionTableSwitchArgumentNode getTableSwitchArgument()
    {
        InstructionTableSwitchArgumentNode argumentNode = PsiTreeUtil.findChildOfType(
                this,
                InstructionTableSwitchArgumentNode.class
        );
        if (argumentNode == null)
            throw new IllegalStateException("Table switch instruction must have an argument node.");
        return argumentNode;
    }

    public int getLowIndex()
    {
        Number lowIndex = this.getTableSwitchArgument().getLowIndex();
        if (lowIndex == null)
            return 0; // Default low index if not specified
        return lowIndex.intValue();
    }

    public LabelNameNode getDefaultBranchLabelName()
    {
        return this.getTableSwitchArgument().getDefaultBranchLabelName();
    }

    @Nullable
    public LabelNameNode[] getBranchLabels()
    {
        return this.getTableSwitchArgument().getBranchLabels();
    }

    @Override
    public int getInstructionSize()
    {
        InstructionTableSwitchArgumentNode argumentNode = this.getTableSwitchArgument();
        Number lowIndexValue = argumentNode.getLowIndex();
        if (lowIndexValue == null)
        {
            lowIndexValue = 0; // Default low index if not specified
        }
        int lowIndex = lowIndexValue.intValue();
        int highIndex = lowIndex + argumentNode.getBranchLabels().length - 1;
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
