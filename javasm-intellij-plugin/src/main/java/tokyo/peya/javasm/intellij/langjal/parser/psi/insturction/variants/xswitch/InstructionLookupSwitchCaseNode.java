package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch;

import com.intellij.lang.ASTNode;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.webSymbols.customElements.json.Return;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.NumberNode;
import tokyo.peya.javasm.langjal.compiler.EvaluatorCommons;

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

    public Number getCaseNumber()
    {
        NumberNode numberNode = PsiTreeUtil.findChildOfType(this, NumberNode.class);
        if (numberNode == null)
            throw new IllegalStateException("Case value is not a number: " + this.getText());

        return EvaluatorCommons.toNumber(numberNode.getText());
    }

    @NotNull
    public LabelNameNode getBranchLabel()
    {
        LabelNameNode labelNameNode = PsiTreeUtil.findChildOfType(this, LabelNameNode.class);
        if (labelNameNode == null)
            throw new IllegalStateException("Branch label is not found in InstructionLookupSwitchCaseNode: " + this.getText());
        return labelNameNode;
    }

    @Override
    public String toString()
    {
        if (this.isDefaultCase())
            return "InstructionLookupSwitchCaseNode(default -> " + this.getBranchLabel().getText() + ")";
        else
            return "InstructionLookupSwitchCaseNode(" + this.getCaseNumber() + " -> " + this.getBranchLabel().getText() + ")";
    }
}
