package tokyo.peya.plugin.javasm.execution;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class BuildRunConfigurationFactory extends ConfigurationFactory
{
    public BuildRunConfigurationFactory(BuildRunConfigurationType buildRunConfigurationType) {}

    @Override
    public @NotNull RunConfiguration createTemplateConfiguration(@NotNull Project project)
    {
        return null;
    }
}
