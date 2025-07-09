package tokyo.peya.javasm.intellij.langjal.parser.psi.identifier;

import com.intellij.psi.tree.IElementType;

public class FullQualifiedNameNode extends IdentifierNode
{
    public FullQualifiedNameNode(IElementType type, CharSequence text)
    {
        super(type, text);
    }

    @Override
    public String toString()
    {
        return "FullQualifiedName(" + this.getText() + ")";
    }
}
