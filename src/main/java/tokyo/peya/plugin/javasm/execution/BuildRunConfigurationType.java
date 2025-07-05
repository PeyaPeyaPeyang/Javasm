package tokyo.peya.plugin.javasm.execution;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import javax.swing.Icon;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.Assets;

public class BuildRunConfigurationType implements ConfigurationType
{
    @Override
    public @NotNull @Nls(capitalization = Nls.Capitalization.Title) String getDisplayName()
    {
        return "Run JavASM Build";
    }

    @Override
    public @Nls(capitalization = Nls.Capitalization.Sentence) String getConfigurationTypeDescription()
    {
        return "Run a JavASM build configuration";
    }

    @Override
    public Icon getIcon()
    {
        return Assets.JAL_ICON;
    }

    @Override
    public @NotNull @NonNls String getId()
    {
        return "JavASM.BuildRunConfigurationType";
    }

    @Override
    public ConfigurationFactory[] getConfigurationFactories()
    {
        return new ConfigurationFactory[]{new BuildRunConfigurationFactory(this)};
    }
}
