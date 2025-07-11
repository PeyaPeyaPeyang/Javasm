package tokyo.peya.javasm.intellij.langjal.parser.psi.clazz;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.jvm.ClassProperty;

public class ClassPropertyNode extends ANTLRPsiNode
{
    public ClassPropertyNode(@NotNull ASTNode node)
    {
        super(node);
    }

    public ClassProperty getPropertyType()
    {
        ClassPropertyNameNode name = PsiTreeUtil.findChildOfType(this, ClassPropertyNameNode.class);
        if (name == null)
            return ClassProperty.UNKNOWN;

        return ClassProperty.fromString(name.getPropertyName());
    }
}
