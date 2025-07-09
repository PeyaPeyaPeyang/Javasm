package tokyo.peya.javasm.intellij.langjal.structureview;

import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

public abstract class JALStructureViewItemPresentationBase implements ItemPresentation
{
    protected final PsiElement psiElement;

    public JALStructureViewItemPresentationBase(PsiElement psiElement)
    {
        this.psiElement = psiElement;
    }

    @Override
    public @Nullable String getPresentableText()
    {
        if (this.psiElement == null)
            return null;

        return this.psiElement.getText();
    }
}
