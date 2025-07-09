package tokyo.peya.javasm.intellij.langjal.parser.psi;

import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.psi.ANTLRPsiLeafNode;

public class BooleanNode extends ANTLRPsiLeafNode
{
    public BooleanNode(IElementType type, CharSequence text)
    {
        super(type, text);
    }

    public boolean toBoolean()
    {
        return Boolean.parseBoolean(this.getText());
    }

    @Override
    public String toString()
    {
        return "Boolean(" + this.getText() + ")";
    }
}
