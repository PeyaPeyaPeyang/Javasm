package tokyo.peya.javasm.intellij.langjal.parser.psi.clazz;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.property.ClassPropertyInterfacesNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.property.ClassPropertyMajorVersionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.property.ClassPropertyMinorVersionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.property.ClassPropertySuperClassNode;

public class ClassMetaNode extends ANTLRPsiNode
{
    public ClassMetaNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @Nullable
    public ClassPropertyMajorVersionNode getMajorVersion()
    {
        return PsiTreeUtil.findChildOfType(this, ClassPropertyMajorVersionNode.class);
    }

    @Nullable
    public ClassPropertyMinorVersionNode getMinorVersion()
    {
        return PsiTreeUtil.findChildOfType(this, ClassPropertyMinorVersionNode.class);
    }

    @Nullable
    public ClassPropertyInterfacesNode getInterfaces()
    {
        return PsiTreeUtil.findChildOfType(this, ClassPropertyInterfacesNode.class);
    }

    @Nullable
    public ClassPropertySuperClassNode getSuperClass()
    {
        return PsiTreeUtil.findChildOfType(this, ClassPropertySuperClassNode.class);
    }
}
