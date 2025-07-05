package tokyo.peya.plugin.javasm.execution;

import com.intellij.codeInsight.daemon.LineMarkerProviderDescriptor;
import com.intellij.execution.lineMarker.ExecutorAction;
import com.intellij.execution.lineMarker.RunLineMarkerContributor;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.plugin.javasm.langjal.psi.JALClassDefinition;
import tokyo.peya.plugin.javasm.langjal.psi.JALMethodDefinition;
import tokyo.peya.plugin.javasm.langjal.psi.JALTokenType;
import tokyo.peya.plugin.javasm.langjal.psi.JALTypes;

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
        if (!psiElement.getNode().getElementType().equals(JALTypes.ID))
            return null;

        PsiElement element = psiElement.getParent();
        if (element instanceof JALClassDefinition classDefinition)
        {
            if (!PSIExecutorUtil.hasMainMethod(classDefinition))
                return null;
        }
        else if (element instanceof JALMethodDefinition methodDefinition)
        {
            if (!PSIExecutorUtil.isMainMethod(methodDefinition))
                return null;
        }
        else
            return null;

        AnAction[] actions = ExecutorAction.getActions(Integer.MAX_VALUE);
        return new Info(AllIcons.RunConfigurations.TestState.Run, actions);
    }
}
