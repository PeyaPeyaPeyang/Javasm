package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;

public class InstructionLookupSwitchNode extends InstructionNode
{
    public InstructionLookupSwitchNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @Nullable
    public InstructionLookupSwitchArgumentNode getTableSwitchArgument()
    {
        return PsiTreeUtil.findChildOfType(
                this,
                InstructionLookupSwitchArgumentNode.class
        );
    }

    @Nullable
    public LabelNameNode getDefaultBranchLabelName()
    {
        InstructionLookupSwitchArgumentNode argumentNode = this.getTableSwitchArgument();
        if (argumentNode == null)
            return null;
        InstructionLookupSwitchCaseNode defaultBranch = argumentNode.getDefault();
        if (defaultBranch == null)
            return null;

        return defaultBranch.getBranchLabel();
    }

    @NotNull
    public InstructionLookupSwitchCaseNode[] getCaseBranches()
    {
        InstructionLookupSwitchArgumentNode argumentNode = this.getTableSwitchArgument();
        if (argumentNode == null)
            return null;
        return argumentNode.getBranches();
    }

    @Override
    public int getInstructionSize()
    {
        InstructionLookupSwitchArgumentNode argumentNode = this.getTableSwitchArgument();
        if (argumentNode == null)
            throw new IllegalStateException();

        int npairs = argumentNode.getBranches().length;

        int baseOffset = this.getStartInstructionOffset(); // assume you have this
        int padding = (4 - (baseOffset + 1) % 4) % 4;

        int size = 1;              // opcode
        size += padding;           // padding
        size += 4;                 // default offset
        size += 4;                 // npairs
        size += 8 * npairs;        // key + offset per pair
        return size;
    }
}
