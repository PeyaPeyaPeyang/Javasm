package tokyo.peya.javasm.intellij.editor.structureview.views;

import com.intellij.util.PlatformIcons;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.editor.structureview.JALStructureViewItemPresentationBase;
import tokyo.peya.javasm.intellij.jvm.AccessAttribute;
import tokyo.peya.javasm.intellij.jvm.AccessAttributeSet;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassDefinitionNode;

public class ClassStructurePresentation extends JALStructureViewItemPresentationBase
{
    public ClassStructurePresentation(@NotNull ClassDefinitionNode element)
    {
        super(element);
    }

    @Override
    public @Nullable String getPresentableText()
    {
        return ((ClassDefinitionNode) this.psiElement).getFullQualifiedClassName();
    }

    @Override
    public @Nullable Icon getIcon(boolean b)
    {
        ClassDefinitionNode classNode = (ClassDefinitionNode) this.psiElement;
        AccessAttributeSet attributes = classNode.getAccessAttributes();

        if (attributes.has(AccessAttribute.ENUM))
            return PlatformIcons.ENUM_ICON;
        else if (attributes.has(AccessAttribute.INTERFACE))
            return PlatformIcons.INTERFACE_ICON;
        else if (attributes.has(AccessAttribute.ABSTRACT))
            return PlatformIcons.ABSTRACT_CLASS_ICON;
        else if (attributes.has(AccessAttribute.INTERFACE))
            return PlatformIcons.INTERFACE_ICON;
        else if (attributes.has(AccessAttribute.ANNOTATION))
            return PlatformIcons.ANNOTATION_TYPE_ICON;
        else
            return PlatformIcons.CLASS_ICON;
    }
}
