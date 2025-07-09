package tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.property;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassPropertyNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierNode;

import java.util.Arrays;

public class ClassPropertySuperClassNode extends ClassPropertyNode
{
    public ClassPropertySuperClassNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @Nullable
    public String[] getInterfaceNames()
    {
        IdentifierNode[] fqNameNode = this.findChildrenByClass(IdentifierNode.class);
        return Arrays.stream(fqNameNode)
                .map(IdentifierNode::getText)
                .toArray(String[]::new);
    }
}
