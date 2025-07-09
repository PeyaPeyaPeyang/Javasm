package tokyo.peya.javasm.intellij.langjal.parser.psi.refs;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.JALElementReference;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.InstructionLocalAccessNode;

public class LocalReference extends JALElementReference
{
    public LocalReference(@NotNull IdentifierNode element)
    {
        super(element);
    }

    @Override
    public boolean isSubtree(PsiElement psiElement)
    {
        return psiElement instanceof InstructionLocalAccessNode;
    }
}
