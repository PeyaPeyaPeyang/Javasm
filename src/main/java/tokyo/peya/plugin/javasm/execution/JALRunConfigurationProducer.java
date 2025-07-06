package tokyo.peya.plugin.javasm.execution;

import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.RunConfigurationProducer;
import com.intellij.execution.application.ApplicationConfiguration;
import com.intellij.execution.application.ApplicationConfigurationType;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.langjal.JALFile;
import tokyo.peya.plugin.javasm.langjal.psi.JALClassDefinition;

public class JALRunConfigurationProducer extends RunConfigurationProducer<ApplicationConfiguration>
{

    public JALRunConfigurationProducer()
    {
        super(false);
    }

    @Override
    protected boolean setupConfigurationFromContext(@NotNull ApplicationConfiguration configuration,
                                                    @NotNull ConfigurationContext context,
                                                    @NotNull Ref<PsiElement> sourceElement)
    {
        PsiElement location = context.getPsiLocation();
        if (location == null || isNonJAL(location.getContainingFile())) return false;

        JALFile jalFile = (JALFile) location.getContainingFile();
        String mainClassName = findMainClassForJal(jalFile);
        if (mainClassName == null)
            return false;

        configuration.setMainClassName(mainClassName);
        configuration.setModule(context.getModule());
        configuration.setName("Run " + mainClassName);

        return true;
    }

    @Override
    public boolean isConfigurationFromContext(
            @NotNull ApplicationConfiguration configuration,
            @NotNull ConfigurationContext context
    )
    {
        PsiElement location = context.getPsiLocation();
        if (location == null || isNonJAL(location.getContainingFile())) return false;

        JALFile jalFile = (JALFile) location.getContainingFile();
        String mainClassName = findMainClassForJal(jalFile);
        if (mainClassName == null)
            return false;

        return mainClassName.equals(configuration.getMainClassName());
    }

    private boolean isNonJAL(PsiFile file)
    {
        return !(file instanceof JALFile && file.getName().endsWith(".jal"));
    }

    private String findMainClassForJal(JALFile file)
    {
        if (!file.isValid())
            return null;

        JALClassDefinition mainClassCandidate = PSIExecutorUtil.findClassForFile(file);
        if (mainClassCandidate == null || !PSIExecutorUtil.hasMainMethod(mainClassCandidate))
            return null;

        if (mainClassCandidate.getClassName() == null)
            return null; // クラス名がない場合はメインクラスとして扱わない

        return mainClassCandidate.getClassName().getText();
    }

    @Override
    public @NotNull ConfigurationFactory getConfigurationFactory()
    {
        return ApplicationConfigurationType.getInstance().getConfigurationFactories()[0];
    }
}
