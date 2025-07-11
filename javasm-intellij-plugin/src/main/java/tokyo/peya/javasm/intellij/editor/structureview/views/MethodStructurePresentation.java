package tokyo.peya.javasm.intellij.editor.structureview.views;

import com.intellij.util.PlatformIcons;
import javax.swing.Icon;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.editor.structureview.JALStructureViewItemPresentationBase;
import tokyo.peya.javasm.intellij.jvm.AccessAttribute;
import tokyo.peya.javasm.intellij.jvm.AccessAttributeSet;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDefinitionNode;

public class MethodStructurePresentation extends JALStructureViewItemPresentationBase
{
    public MethodStructurePresentation(MethodDefinitionNode methodElement)
    {
        super(methodElement);
    }

    @Override
    public @Nullable Icon getIcon(boolean b)
    {
        MethodDefinitionNode methodNode = (MethodDefinitionNode) this.psiElement;
        AccessAttributeSet attributes = methodNode.getAccessAttributes();
        if (attributes.has(AccessAttribute.ABSTRACT))
            return PlatformIcons.ABSTRACT_METHOD_ICON;
        else
            return PlatformIcons.METHOD_ICON;
    }

    @Override
    public @Nullable String getPresentableText()
    {
        MethodDefinitionNode methodNode = (MethodDefinitionNode) this.psiElement;
        return methodNode.getMethodName() + methodNode.getMethodDescriptor();
    }
}
