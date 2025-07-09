package tokyo.peya.javasm.intellij.langjal.parser.psi.identifier;

import com.intellij.psi.tree.IElementType;

public class IdentifierMethodInitNode extends IdentifierNode
{
    public IdentifierMethodInitNode(IElementType type)
    {
        super(type, "<init>");
    }
}
