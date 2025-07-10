package tokyo.peya.javasm.intellij.langjal.structureview.views;

import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassDefinitionNode;
import tokyo.peya.javasm.intellij.langjal.structureview.JALStructureViewElementBase;

import java.util.Arrays;

public class ClassStructureView extends JALStructureViewElementBase
{
    public ClassStructureView(@NotNull ClassDefinitionNode clazz)
    {
        super(clazz);
    }

    @Override
    public @NotNull ItemPresentation getPresentation()
    {
        return new ClassStructurePresentation((ClassDefinitionNode) this.element);
    }

    @Override
    public TreeElement @NotNull [] getChildren()
    {
        ClassDefinitionNode classNode = (ClassDefinitionNode) this.element;
        return Arrays.stream(classNode.getMethods())
                     .map(MethodStructureView::new)
                     .toArray(TreeElement[]::new);
    }
}
