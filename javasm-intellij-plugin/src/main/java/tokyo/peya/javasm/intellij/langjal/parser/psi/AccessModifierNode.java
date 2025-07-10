package tokyo.peya.javasm.intellij.langjal.parser.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.jvm.AccessAttributeSet;
import tokyo.peya.javasm.intellij.jvm.AccessLevel;

import java.util.Arrays;
import java.util.stream.Collectors;

public class AccessModifierNode extends ANTLRPsiNode
{
    public AccessModifierNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public AccessLevel getAccessLevel()
    {
        AccessLevelNode accessLevelElement = PsiTreeUtil.findChildOfType(this, AccessLevelNode.class);
        if (accessLevelElement == null)
            return AccessLevel.PACKAGE_PRIVATE;

        return accessLevelElement.getAccessLevel();
    }

    @NotNull
    public AccessAttributeSet getAccessAttributes()
    {
        PsiElement[] accessAttributeElements = PsiTreeUtil.getChildrenOfType(this, AccessAttributeNode.class);
        if (accessAttributeElements == null || accessAttributeElements.length == 0)
            return AccessAttributeSet.EMPTY;

        return new AccessAttributeSet(
                Arrays.stream(accessAttributeElements)
                      .map(element -> (AccessAttributeNode) element)
                      .map(AccessAttributeNode::getAccessAttribute)
                      .collect(Collectors.toSet())
        );
    }
}
