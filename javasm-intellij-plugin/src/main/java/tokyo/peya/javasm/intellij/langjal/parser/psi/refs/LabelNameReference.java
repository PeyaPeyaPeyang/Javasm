package tokyo.peya.javasm.intellij.langjal.parser.psi.refs;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.JALElementReference;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.InstructionSetNode;

public class LabelNameReference extends JALElementReference
{
    public LabelNameReference(@NotNull IdentifierNode element)
    {
        super(element);
    }

    @Override
    public boolean isSubtree(PsiElement psiElement)
    {
        return psiElement instanceof LabelNode || psiElement instanceof LabelNameNode || psiElement instanceof InstructionSetNode;
    }
}
