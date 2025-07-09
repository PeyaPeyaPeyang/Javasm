package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.NumberNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;

public class InstructionNumericArgumentNode extends InstructionNode
{
    public InstructionNumericArgumentNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public Number getNumberArgument()
    {
        NumberNode numberNode = PsiTreeUtil.findChildOfType(this, NumberNode.class);
        if (numberNode == null)
            throw new IllegalStateException("Number argument is missing in instruction: " + getInstructionName());
        return numberNode.toNumber();
    }

    @Override
    public String toString()
    {
        return "InstructionNode(" + this.getInstructionName() + " num=" + this.getNumberArgument() + ")";
    }
}
