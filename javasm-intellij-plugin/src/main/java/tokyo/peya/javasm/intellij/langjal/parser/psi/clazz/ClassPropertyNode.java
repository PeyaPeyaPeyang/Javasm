package tokyo.peya.javasm.intellij.langjal.parser.psi.clazz;

import com.intellij.lang.ASTNode;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.jvm.ClassProperty;

public class ClassPropertyNode extends ANTLRPsiNode
{
    public ClassPropertyNode(@NotNull ASTNode node)
    {
        super(node);
    }

    public ClassProperty getPropertyType()
    {
        ClassPropertyNameNode name = this.findChildByClass(ClassPropertyNameNode.class);
        if (name == null)
            return ClassProperty.UNKNOWN;

        return ClassProperty.fromString(name.getPropertyName());
    }
}
