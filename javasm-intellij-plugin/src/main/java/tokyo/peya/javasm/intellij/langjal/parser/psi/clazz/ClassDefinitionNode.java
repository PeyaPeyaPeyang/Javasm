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
import tokyo.peya.javasm.intellij.langjal.parser.psi.AccessModifierNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDefinitionNode;
import tokyo.peya.javasm.langjal.compiler.jvm.AccessAttributeSet;
import tokyo.peya.javasm.langjal.compiler.jvm.AccessLevel;

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
    public ClassNameNode getClassNameNode()
    {
        ClassNameNode classNameNode = PsiTreeUtil.findChildOfType(this, ClassNameNode.class);
        if (classNameNode == null)
            throw new IllegalStateException("Class name node not found in " + this.getText());
        return classNameNode;
    }

    @NotNull
    public String getFullQualifiedClassName()
    {
        ClassNameNode classNameNode = PsiTreeUtil.findChildOfType(this, ClassNameNode.class);
        if (classNameNode == null)
            return "<unknown>";
        else
            return classNameNode.getText();
    }

    @NotNull
    public String getClassName()
    {
        String fullQualifiedClassName = getFullQualifiedClassName();
        int lastSlashIndex = fullQualifiedClassName.lastIndexOf('/');
        if (lastSlashIndex == -1)
            return fullQualifiedClassName; // パッケージ名がない場合はそのまま返す
        else
            return fullQualifiedClassName.substring(lastSlashIndex + 1); // 最後のスラッシュ以降の部分を返す
    }

    @NotNull
    public String getPackageName()
    {
        String fullQualifiedClassName = getFullQualifiedClassName();
        int lastSlashIndex = fullQualifiedClassName.lastIndexOf('/');
        if (lastSlashIndex == -1)
            return ""; // パッケージ名がない場合は空文字列を返す
        else
            return fullQualifiedClassName.substring(0, lastSlashIndex); // 最後のスラッシュまでの部分を返す
    }

    @NotNull
    public tokyo.peya.javasm.langjal.compiler.jvm.AccessLevel getAccessLevel()
    {
        AccessModifierNode accessLevelNode = PsiTreeUtil.findChildOfType(this, AccessModifierNode.class);
        if (accessLevelNode == null)
            return AccessLevel.PACKAGE_PRIVATE;

        return accessLevelNode.getAccessLevel();
    }

    @NotNull
    public AccessAttributeSet getAccessAttributes()
    {
        AccessModifierNode accessModifierNode = PsiTreeUtil.findChildOfType(this, AccessModifierNode.class);
        if (accessModifierNode == null)
            return AccessAttributeSet.EMPTY;

        return accessModifierNode.getAccessAttributes();
    }

    @NotNull
    public MethodDefinitionNode[] getMethods()
    {
        ClassBodyNode classBodyNode = PsiTreeUtil.findChildOfType(this, ClassBodyNode.class);
        if (classBodyNode == null)
            return new MethodDefinitionNode[0];

        List<ClassBodyItemNode> bodyItems = PsiTreeUtil.getChildrenOfTypeAsList(classBodyNode, ClassBodyItemNode.class);
        return bodyItems.stream()
                        .filter(ClassBodyItemNode::isMethod)
                        .map(ClassBodyItemNode::getMethod)
                        .filter(Objects::nonNull)
                        .toArray(MethodDefinitionNode[]::new);
    }
}
