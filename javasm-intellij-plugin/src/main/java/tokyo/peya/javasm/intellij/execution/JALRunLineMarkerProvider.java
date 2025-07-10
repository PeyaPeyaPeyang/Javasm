package tokyo.peya.javasm.intellij.execution;

import com.intellij.execution.lineMarker.ExecutorAction;
import com.intellij.execution.lineMarker.RunLineMarkerContributor;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassDefinitionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDefinitionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodNameNode;

public class JALRunLineMarkerProvider extends RunLineMarkerContributor
{
    @Override
    public boolean isDumbAware()
    {
        return this.getClass().isAssignableFrom(JALRunLineMarkerProvider.class);
    }

    @Override
    public @Nullable Info getInfo(@NotNull PsiElement psiElement)
    {
        // ソース・ルート内にある必要がある
        if (!PSIExecutorUtil.isInSourceRoot(psiElement.getContainingFile()))
            return null;

        PsiElement element = psiElement.getParent();
        if (!(element instanceof MethodNameNode methodName))
            return null;

        if (!(methodName.getParent() instanceof MethodDefinitionNode methodNode))
            return null;

        // メソッドが実行可能であることを確認
        if (!PSIExecutorUtil.isMainMethod(methodNode))
            return null;

        ClassDefinitionNode classDefinition = methodNode.getContainingClass();
        if (classDefinition == null)
            return null;

        // クラスが実行可能であることを確認
        if (!PSIExecutorUtil.isAccessibleClass(classDefinition))
            return null;

        AnAction[] actions = ExecutorAction.getActions(Integer.MAX_VALUE);
        return new Info(AllIcons.RunConfigurations.TestState.Run, actions);


    }
}
