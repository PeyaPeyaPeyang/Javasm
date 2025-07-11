package tokyo.peya.javasm.intellij.editor.structureview;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.pom.Navigatable;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public abstract class JALStructureViewElementBase implements StructureViewTreeElement
{
    protected final PsiElement element;

    public JALStructureViewElementBase(@NotNull NavigatablePsiElement element)
    {
        this.element = element;
    }

    @Override
    public boolean canNavigateToSource()
    {
        Navigatable navigatable = (Navigatable) this.element;
        return navigatable.canNavigateToSource();
    }

    @Override
    public boolean canNavigate()
    {
        Navigatable navigatable = (Navigatable) this.element;
        return navigatable.canNavigate();
    }

    @Override
    public void navigate(boolean requestFocus)
    {
        Navigatable navigatable = (Navigatable) this.element;
        navigatable.navigate(requestFocus);
    }

    @Override
    public Object getValue()
    {
        return this.element;
    }
}
