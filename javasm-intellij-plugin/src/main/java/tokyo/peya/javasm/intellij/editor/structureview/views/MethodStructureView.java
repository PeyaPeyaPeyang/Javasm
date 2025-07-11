package tokyo.peya.javasm.intellij.editor.structureview.views;

import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.editor.structureview.JALStructureViewElementBase;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.InstructionSetNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDefinitionNode;

import java.util.Arrays;

public class MethodStructureView extends JALStructureViewElementBase
{
    public MethodStructureView(@NotNull MethodDefinitionNode element)
    {
        super(element);
    }

    @Override
    public @NotNull ItemPresentation getPresentation()
    {
        return new MethodStructurePresentation((MethodDefinitionNode) this.element);
    }

    @Override
    public TreeElement @NotNull [] getChildren()
    {
        MethodDefinitionNode methodNode = (MethodDefinitionNode) this.element;
        InstructionSetNode[] instructionSet = methodNode.getInstructionSets();
        if (instructionSet == null)
            return TreeElement.EMPTY_ARRAY;

        return Arrays.stream(instructionSet)
                     .map(InstructionSetStructureView::new)
                     .toArray(TreeElement[]::new);
    }
}
