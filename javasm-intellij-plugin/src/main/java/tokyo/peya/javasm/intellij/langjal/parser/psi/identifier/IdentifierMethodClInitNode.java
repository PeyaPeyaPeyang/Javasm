package tokyo.peya.javasm.intellij.langjal.parser.psi.identifier;

import com.intellij.psi.tree.IElementType;

public class IdentifierMethodClInitNode extends IdentifierNode
{
    public IdentifierMethodClInitNode(IElementType type)
    {
        super(type, "<clinit>");
    }
}
