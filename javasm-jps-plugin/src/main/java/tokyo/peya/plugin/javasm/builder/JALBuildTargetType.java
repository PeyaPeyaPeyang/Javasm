package tokyo.peya.plugin.javasm.builder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.builders.BuildTargetLoader;
import org.jetbrains.jps.builders.BuildTargetType;
import org.jetbrains.jps.model.JpsModel;
import org.jetbrains.jps.model.module.JpsModule;

import java.util.List;

public class JALBuildTargetType extends BuildTargetType<JALBuildTarget>
{

    public static final String TYPE_ID = "JAL";
    private static final JALBuildTargetType INSTANCE = new JALBuildTargetType();

    private JALBuildTargetType()
    {
        super(TYPE_ID);
    }

    @Override
    public @NotNull List<JALBuildTarget> computeAllTargets(@NotNull JpsModel jpsModel)
    {
        return List.of();
    }

    @Override
    public @NotNull BuildTargetLoader<JALBuildTarget> createLoader(@NotNull JpsModel model)
    {
        return new BuildTargetLoader<>()
        {
            @Override
            public @Nullable JALBuildTarget createTarget(@NotNull String targetId)
            {
                for (JpsModule module : model.getProject().getModules())
                {
                    if (module.getName().equals(targetId))
                        return new JALBuildTarget(module);
                }
                return null;
            }
        };
    }

    public static JALBuildTargetType getInstance()
    {
        return INSTANCE;
    }
}
