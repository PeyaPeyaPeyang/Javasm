package tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.property;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassPropertyNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierNode;

import java.util.Arrays;

public class ClassPropertyInterfacesNode extends ClassPropertyNode
{
    public ClassPropertyInterfacesNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @Nullable
    public String[] getInterfaces()
    {
        IdentifierNode[] identifierNodes = this.findChildrenByClass(IdentifierNode.class);
        return Arrays.stream(identifierNodes)
                .map(IdentifierNode::getName)
                .toArray(String[]::new);
    }
}
