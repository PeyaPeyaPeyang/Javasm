package tokyo.peya.javasm.intellij.langjal.parser.psi.clazz;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.adaptor.SymtabUtils;
import org.antlr.intellij.adaptor.psi.IdentifierDefSubtree;
import org.antlr.intellij.adaptor.psi.ScopeNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.JALLanguage;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDefinitionNode;

import java.util.List;
import java.util.Objects;

public class ClassDefinitionNode extends IdentifierDefSubtree implements ScopeNode
{
    public ClassDefinitionNode(@NotNull ASTNode node, @NotNull IElementType idElementType)
    {
        super(node, idElementType);
    }

    @Override
    public @Nullable PsiElement resolve(PsiNamedElement element)
    {
        return SymtabUtils.resolve(this, JALLanguage.INSTANCE, element, "/root/classDefinition/className");
    }


    @NotNull
    public MethodDefinitionNode[] getMethods()
    {
        List<ClassBodyItemNode> bodyItems = PsiTreeUtil.getChildrenOfTypeAsList(this, ClassBodyItemNode.class);
        return bodyItems.stream()
                .filter(ClassBodyItemNode::isMethod)
                .map(ClassBodyItemNode::getMethod)
                .filter(Objects::nonNull)
                .toArray(MethodDefinitionNode[]::new);
    }

}
