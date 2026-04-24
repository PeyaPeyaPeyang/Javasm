package tokyo.peya.javasm.intellij.langjal.parser.psi.identifier;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    @Nullable
    public String getDotName()
    {
        return this.getText().replace('/', '.');
    }
}
