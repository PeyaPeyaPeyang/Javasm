package tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.property;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassPropertyNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.NumberNode;

public class ClassPropertyMajorVersionNode extends ClassPropertyNode
{
    public ClassPropertyMajorVersionNode(@NotNull ASTNode node)
    {
        super(node);
    }

    public int getMajorVersion()
    {
         NumberNode numberNode = this.findChildByClass(NumberNode.class);
         return numberNode != null ? numberNode.toNumber().intValue() : 0;
    }
}
