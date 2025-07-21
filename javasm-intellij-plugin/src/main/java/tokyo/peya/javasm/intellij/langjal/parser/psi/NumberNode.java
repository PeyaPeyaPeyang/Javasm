package tokyo.peya.javasm.intellij.langjal.parser.psi;

import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.psi.ANTLRPsiLeafNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.langjal.compiler.utils.EvaluatorCommons;

public class NumberNode extends ANTLRPsiLeafNode
{
    public NumberNode(IElementType type, CharSequence text)
    {
        super(type, text);
    }

    @NotNull
    public Number toNumber()
    {
        return EvaluatorCommons.toNumber(this.getText());
    }

    @Override
    public String toString()
    {
        return "Number(" + this.getText() + ")";
    }
}
