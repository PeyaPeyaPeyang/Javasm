package tokyo.peya.javasm.intellij.editor.structureview;

import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.editor.structureview.views.JALFileStructureView;
import tokyo.peya.javasm.intellij.langjal.JALFile;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassDefinitionNode;

public class JALStructureViewModel extends StructureViewModelBase implements StructureViewModelBase.ElementInfoProvider
{
    public JALStructureViewModel(@NotNull JALFile jalFile)
    {
        super(jalFile, new JALFileStructureView(jalFile));
    }

    @Override
    public Sorter @NotNull [] getSorters()
    {
        return new Sorter[]{
                Sorter.ALPHA_SORTER
        };
    }

    @Override
    public boolean isAlwaysShowsPlus(StructureViewTreeElement structureViewTreeElement)
    {
        Object value = structureViewTreeElement.getValue();
        return value instanceof ClassDefinitionNode || value instanceof JALFile;
    }

    @Override
    public boolean isAlwaysLeaf(StructureViewTreeElement structureViewTreeElement)
    {
        Object value = structureViewTreeElement.getValue();
        return value instanceof JALFileStructureView;
    }
}
