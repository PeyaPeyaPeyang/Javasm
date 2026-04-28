package tokyo.peya.javasm.intellij.langjal;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiPackage;
import com.intellij.util.PlatformIcons;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.Assets;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class JALCreateFileFromTemplateAction extends CreateFileFromTemplateAction {
    public JALCreateFileFromTemplateAction() {
        super("JAL Class", "Create a new JAL class file", Assets.JAL);
    }

    @Override
    protected void buildDialog(@NotNull Project project, @NotNull PsiDirectory directory,
                               CreateFileFromTemplateDialog.@NotNull Builder builder) {
        builder.setTitle("New JAL File")
                .addKind("JAL class", PlatformIcons.CLASS_ICON, "JAL Class");
    }

    @Override
    protected @NlsContexts.Command String getActionName(PsiDirectory directory, @NonNls @NotNull String newName,
                                                        @NonNls String templateName) {
        return "Create JAL Class '" + newName + "'";
    }
    @Override
    protected PsiFile createFileFromTemplate(String name, FileTemplate template, PsiDirectory dir) {

        PsiPackage psiPackage = JavaDirectoryService.getInstance().getPackage(dir);
        String packageName = psiPackage != null ? psiPackage.getQualifiedName() : "";

        String fullQualifiedName = (packageName.isEmpty() ? name : packageName + "." + name);

        String pathName = StringUtils.replaceChars(name, '.', '/');
        String slashFqn = StringUtils.replaceChars(fullQualifiedName, '.', '/');

        Map<String, String> extraProps = new HashMap<>();
        extraProps.put("FULL_QUALIFIED_NAME", slashFqn);

        return CreateFileFromTemplateAction.createFileFromTemplate(
                pathName,
                template,
                dir,
                getDefaultTemplateProperty(),
                true,
                Collections.emptyMap(),
                extraProps
        );
    }
}
