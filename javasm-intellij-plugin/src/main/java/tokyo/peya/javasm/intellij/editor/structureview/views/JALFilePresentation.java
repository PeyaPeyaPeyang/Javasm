package tokyo.peya.javasm.intellij.editor.structureview.views;

import com.intellij.psi.PsiElement;
import javax.swing.Icon;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.Assets;
import tokyo.peya.javasm.intellij.editor.structureview.JALStructureViewItemPresentationBase;

public class JALFilePresentation extends JALStructureViewItemPresentationBase
{
    public JALFilePresentation(PsiElement psiElement)
    {
        super(psiElement);
    }

    @Override
    public @Nullable Icon getIcon(boolean b)
    {
        return Assets.JAL;
    }
}
