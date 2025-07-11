package tokyo.peya.javasm.intellij.editor.structureview.views;

import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.editor.structureview.JALStructureViewElementBase;
import tokyo.peya.javasm.intellij.langjal.JALFile;

public class JALFileStructureView extends JALStructureViewElementBase
{
    public JALFileStructureView(@NotNull JALFile jalFile)
    {
        super(jalFile);
    }

    @Override
    public @NotNull ItemPresentation getPresentation()
    {
        return new JALFilePresentation(this.element);
    }

    @Override
    public TreeElement @NotNull [] getChildren()
    {
        JALFile jalFile = (JALFile) this.element;
        if (jalFile.getClassDefinition() == null)
            return TreeElement.EMPTY_ARRAY;

        return new TreeElement[]{new ClassStructureView(jalFile.getClassDefinition())};
    }
}
