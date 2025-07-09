package tokyo.peya.javasm.intellij.langjal.parser.psi.method;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.AccessModifier;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.adaptor.SymtabUtils;
import org.antlr.intellij.adaptor.psi.IdentifierDefSubtree;
import org.antlr.intellij.adaptor.psi.ScopeNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.jvm.AccessAttribute;
import tokyo.peya.javasm.intellij.jvm.AccessAttributeSet;
import tokyo.peya.javasm.intellij.jvm.AccessLevel;
import tokyo.peya.javasm.intellij.jvm.MethodDescriptor;
import tokyo.peya.javasm.intellij.langjal.JALLanguage;
import tokyo.peya.javasm.intellij.langjal.parser.psi.AccessModifierNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassBodyItemNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassBodyNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassDefinitionNode;

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

    @Nullable
    public ClassDefinitionNode getContainingClass()
    {
        if (!(this.getParent() instanceof ClassBodyItemNode classBodyItem))
            return null;
        if (!(classBodyItem.getParent() instanceof ClassBodyNode classBody))
            return null;
        if (!(classBody.getParent() instanceof ClassDefinitionNode classDefinition))
            return null;

        return classDefinition;
    }

    @NotNull
    public InstructionSetNode[] getInstructionSets()
    {
        MethodBodyNode methodBody = PsiTreeUtil.findChildOfType(this, MethodBodyNode.class);
        if (methodBody == null)
            return new InstructionSetNode[0];

        InstructionSetNode[] instructionSets = PsiTreeUtil.getChildrenOfType(methodBody, InstructionSetNode.class);
        if (instructionSets == null)
            return new InstructionSetNode[0];
        return instructionSets;
    }

    @NotNull
    public String getMethodName()
    {
        PsiElement nameElement = PsiTreeUtil.findChildOfType(this, MethodNameNode.class);
        if (nameElement == null)
            return "<unknown>";
        else
            return nameElement.getText();
    }

    @NotNull
    public MethodDescriptor getMethodDescriptor()
    {
        PsiElement descriptorElement = PsiTreeUtil.findChildOfType(this, MethodDescriptorNode.class);
        if (descriptorElement == null)
            throw new IllegalStateException("Method descriptor not found in method definition node");
        return MethodDescriptor.parse(descriptorElement.getText());
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
