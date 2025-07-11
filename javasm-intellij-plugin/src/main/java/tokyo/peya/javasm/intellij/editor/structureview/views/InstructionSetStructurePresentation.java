package tokyo.peya.javasm.intellij.editor.structureview.views;

import com.intellij.icons.AllIcons;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.editor.structureview.JALStructureViewItemPresentationBase;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.InstructionSetNode;

public class InstructionSetStructurePresentation extends JALStructureViewItemPresentationBase
{
    public InstructionSetStructurePresentation(@NotNull InstructionSetNode instructionSetNode)
    {
        super(instructionSetNode);
    }

    @Override
    public @Nullable String getPresentableText()
    {
        InstructionSetNode instructionSetNode = (InstructionSetNode) this.psiElement;
        LabelNode labelNode = instructionSetNode.getLabel();
        if (labelNode == null)
            return "instructions:";
        else
            return labelNode.getText();

    }

    @Override
    public @Nullable Icon getIcon(boolean b)
    {
        return AllIcons.Nodes.Tag;
    }
}
