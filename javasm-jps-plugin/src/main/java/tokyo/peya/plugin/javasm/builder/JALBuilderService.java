package tokyo.peya.plugin.javasm.builder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.builders.BuildTargetType;
import org.jetbrains.jps.incremental.BuilderService;
import org.jetbrains.jps.incremental.ModuleLevelBuilder;
import org.jetbrains.jps.incremental.TargetBuilder;
import org.jetbrains.jps.incremental.dependencies.ProjectDependenciesResolver;
import org.jetbrains.jps.incremental.resources.ResourcesBuilder;

import java.util.List;

public class JALBuilderService extends BuilderService
{
    @Override
    public @NotNull List<? extends BuildTargetType<?>> getTargetTypes()
    {
        return List.of(JALBuildTargetType.getInstance());
    }

    @Override
    public @NotNull List<? extends ModuleLevelBuilder> createModuleLevelBuilders()
    {
        return List.of(new JALProjectBuilder());
    }

    public @NotNull List<? extends TargetBuilder<?, ?>> createBuilders()
    {
        return List.of(new ResourcesBuilder(), new ProjectDependenciesResolver());
    }
}
