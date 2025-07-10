package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.NumberNode;

public class InstructionMultiANewArrayNode extends InstructionTypeArgumentNode
{
    public InstructionMultiANewArrayNode(@NotNull ASTNode node)
    {
        super(node);
    }

    public int getDimensions()
    {
        NumberNode numberNode = PsiTreeUtil.findChildOfType(this, NumberNode.class);
        return numberNode != null ? numberNode.toNumber().intValue(): 0;
    }
}
