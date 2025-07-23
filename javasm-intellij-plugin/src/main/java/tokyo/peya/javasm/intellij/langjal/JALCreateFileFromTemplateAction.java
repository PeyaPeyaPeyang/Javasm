package tokyo.peya.javasm.intellij.langjal;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.psi.PsiDirectory;
import com.intellij.util.PlatformIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.Assets;

public class JALCreateFileFromTemplateAction extends CreateFileFromTemplateAction
{
    public JALCreateFileFromTemplateAction()
    {
        super("JAL Class", "Create a new JAL class file", Assets.JAL);
    }

    @Override
    protected void buildDialog(@NotNull Project project, @NotNull PsiDirectory directory,
                               CreateFileFromTemplateDialog.@NotNull Builder builder)
    {
        builder.setTitle("New JAL File")
               .addKind("JAL class", PlatformIcons.CLASS_ICON, "JAL Class");
    }

    @Override
    protected @NlsContexts.Command String getActionName(PsiDirectory directory, @NonNls @NotNull String newName,
                                                        @NonNls String templateName)
    {
        return "Create JAL Class '" + newName + "'";
    }
}
