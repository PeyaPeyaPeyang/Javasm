package tokyo.peya.javasm.intellij.execution;

import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.RunConfigurationProducer;
import com.intellij.execution.application.ApplicationConfiguration;
import com.intellij.execution.application.ApplicationConfigurationType;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.JALFile;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassDefinitionNode;

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

        ClassDefinitionNode mainClassCandidate = file.getClassDefinition();
        if (mainClassCandidate == null || !PSIExecutorUtil.hasMainMethod(mainClassCandidate))
            return null;

        return mainClassCandidate.getFullQualifiedClassName();
    }

    @Override
    public @NotNull ConfigurationFactory getConfigurationFactory()
    {
        return ApplicationConfigurationType.getInstance().getConfigurationFactories()[0];
    }
}
