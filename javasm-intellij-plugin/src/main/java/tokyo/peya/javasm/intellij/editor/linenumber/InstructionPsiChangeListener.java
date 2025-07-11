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
    public void childrenChanged(@NotNull PsiTreeChangeEvent event)
    {
        PsiElement changed = event.getParent();
        if (changed == null || !(changed.getContainingFile() instanceof JALFile))
            return;

        MethodBodyNode methodBody = PsiTreeUtil.getParentOfType(changed, MethodBodyNode.class);
        if (methodBody == null)
            return; // メソッドボディでない場合は無視

        InstructionOffsetCalculator.invalidate(methodBody);
    }
}
