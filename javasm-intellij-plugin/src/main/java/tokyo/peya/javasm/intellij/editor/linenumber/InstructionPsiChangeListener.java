package tokyo.peya.javasm.intellij.editor.linenumber;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiTreeChangeAdapter;
import com.intellij.psi.PsiTreeChangeEvent;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.JALFile;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodBodyNode;

public class InstructionPsiChangeListener extends PsiTreeChangeAdapter
{
    @Override
    public void childAdded(@NotNull PsiTreeChangeEvent event)
    {
        PsiElement child = event.getParent();
        if (child != null)
            invalidateElement(child);
    }

    @Override
    public void childRemoved(@NotNull PsiTreeChangeEvent event)
    {
        PsiElement child = event.getParent();
        if (child != null)
            invalidateElement(child);
    }

    @Override
    public void childReplaced(@NotNull PsiTreeChangeEvent event)
    {
        PsiElement child = event.getParent();
        if (child != null)
            invalidateElement(child);
    }

    @Override
    public void childMoved(@NotNull PsiTreeChangeEvent event)
    {
        PsiElement child = event.getParent();
        if (child != null)
            invalidateElement(child);
    }

    private static void invalidateElement(@NotNull PsiElement element)
    {
        if (!(element.getContainingFile() instanceof JALFile))
            return;

        MethodBodyNode methodBody = PsiTreeUtil.getParentOfType(element, MethodBodyNode.class);
        if (methodBody == null)
            return; // メソッドボディでない場合は無視

        InstructionOffsetCalculator.invalidate(methodBody);

    }

}
