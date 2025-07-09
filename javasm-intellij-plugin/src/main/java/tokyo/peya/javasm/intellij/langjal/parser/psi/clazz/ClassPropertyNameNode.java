package tokyo.peya.javasm.intellij.langjal.parser.psi.clazz;

import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.psi.ANTLRPsiLeafNode;

public class ClassPropertyNameNode extends ANTLRPsiLeafNode
{

    public ClassPropertyNameNode(IElementType type, CharSequence text)
    {
        super(type, text);
    }

    public String getPropertyName()
    {
        return this.getText();
    }

    @Override
    public String toString()
    {
        return "ClassPropertyName(" + this.getText() + ")";
    }
}
