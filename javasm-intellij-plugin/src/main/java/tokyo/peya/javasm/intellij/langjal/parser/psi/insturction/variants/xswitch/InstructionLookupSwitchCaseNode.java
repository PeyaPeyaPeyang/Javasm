package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch;

import com.intellij.lang.ASTNode;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.NumberNode;
import tokyo.peya.javasm.langjal.compiler.utils.EvaluatorCommons;

public class InstructionLookupSwitchCaseNode extends ANTLRPsiNode
{
    public InstructionLookupSwitchCaseNode(@NotNull ASTNode node)
    {
        super(node);
    }

    public boolean isDefaultCase()
    {
        LeafPsiElement defaultKeyword = PsiTreeUtil.findChildOfType(this, LeafPsiElement.class);
        if (defaultKeyword == null)
            return false;
        return "default".equals(defaultKeyword.getText());
    }

    @Nullable
    public Number getCaseNumber()
    {
        NumberNode numberNode = PsiTreeUtil.findChildOfType(this, NumberNode.class);
        if (numberNode == null)
            return null;

        return EvaluatorCommons.toNumber(numberNode.getText());
    }

    @Nullable
    public LabelNameNode getBranchLabel()
    {
        return PsiTreeUtil.findChildOfType(this, LabelNameNode.class);
    }

    @Override
    public String toString()
    {
        if (this.isDefaultCase())
            return "InstructionLookupSwitchCaseNode(default -> " + this.getBranchLabel() + ")";
        else
            return "InstructionLookupSwitchCaseNode(" + this.getCaseNumber() + " -> " + this.getBranchLabel() + ")";
    }
}
