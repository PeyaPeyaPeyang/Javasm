package tokyo.peya.javasm.intellij.editor.structureview.views;

import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.editor.structureview.JALStructureViewElementBase;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.InstructionSetNode;

public class InstructionSetStructureView extends JALStructureViewElementBase
{
    public InstructionSetStructureView(@NotNull InstructionSetNode instructionSetNode)
    {
        super(instructionSetNode);
    }

    @Override
    public @NotNull ItemPresentation getPresentation()
    {
        return new InstructionSetStructurePresentation((InstructionSetNode) this.element);
    }

    @Override
    public TreeElement @NotNull [] getChildren()
    {
        return new TreeElement[0];
    }
}
