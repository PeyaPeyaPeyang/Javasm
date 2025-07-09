package tokyo.peya.javasm.intellij.langjal.parser.psi.method;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.AccessModifier;
import org.antlr.intellij.adaptor.SymtabUtils;
import org.antlr.intellij.adaptor.psi.IdentifierDefSubtree;
import org.antlr.intellij.adaptor.psi.ScopeNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.jvm.AccessAttribute;
import tokyo.peya.javasm.intellij.jvm.AccessAttributeSet;
import tokyo.peya.javasm.intellij.jvm.AccessLevel;
import tokyo.peya.javasm.intellij.langjal.JALLanguage;
import tokyo.peya.javasm.intellij.langjal.parser.psi.AccessModifierNode;

public class MethodDefinitionNode extends IdentifierDefSubtree implements ScopeNode
{
    public MethodDefinitionNode(@NotNull ASTNode node, @NotNull IElementType idElementType)
    {
        super(node, idElementType);
    }

    @Override
    public @Nullable PsiElement resolve(PsiNamedElement element)
    {
        return SymtabUtils.resolve(
                this,
                JALLanguage.INSTANCE,
                element,
                "/classDefinition/classBody/classBodyItem/methodDefinition/methodName"
        );
    }

    public @Nullable AccessModifierNode getAccessModifier()
    {
        return this.findNotNullChildByClass(AccessModifierNode.class);
    }

    @NotNull
    public AccessLevel getAccessLevel()
    {
        AccessModifierNode accessModifier = this.getAccessModifier();
        if (accessModifier != null)
            return accessModifier.getAccessLevel();
        return AccessLevel.PROTECTED; // Default access level if not specified
    }

    @NotNull
    public AccessAttributeSet getAccessAttributes()
    {
        AccessModifierNode accessModifier = this.getAccessModifier();
        if (accessModifier == null)
            return AccessAttributeSet.EMPTY;

        return accessModifier.getAccessAttributes();
    }
}
