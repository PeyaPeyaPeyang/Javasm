package tokyo.peya.plugin.javasm.builder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.jetbrains.jps.builders.BuildRootIndex;
import org.jetbrains.jps.builders.BuildTarget;
import org.jetbrains.jps.builders.BuildTargetRegistry;
import org.jetbrains.jps.builders.TargetOutputIndex;
import org.jetbrains.jps.builders.storage.BuildDataPaths;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.indices.IgnoredFileIndex;
import org.jetbrains.jps.indices.ModuleExcludeIndex;
import org.jetbrains.jps.model.JpsModel;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.model.module.JpsModuleSourceRoot;
import org.jetbrains.jps.util.JpsPathUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JALBuildTarget extends BuildTarget<JALBuildRootDescriptor>
{
    private final JpsModule module;

    protected JALBuildTarget(@NotNull JpsModule module)
    {
        super(JALBuildTargetType.getInstance());
        this.module = module;
    }

    @Override
    public @NotNull String getPresentableName()
    {
        return "JAL Build Target for " + this.module.getName();
    }

    @Override
    public @NotNull @Unmodifiable Collection<File> getOutputRoots(@NotNull CompileContext context)
    {
        return super.getOutputRoots(context);
    }

    @Override
    public @NotNull String getId()
    {
        return "JALBuildTarget:" + this.module.getName();
    }

    @Override
    public @NotNull Collection<BuildTarget<?>> computeDependencies(@NotNull BuildTargetRegistry buildTargetRegistry,
                                                                   @NotNull TargetOutputIndex targetOutputIndex)
    {
        return List.of();
    }

    @Override
    public @NotNull @Unmodifiable List<JALBuildRootDescriptor> computeRootDescriptors(@NotNull JpsModel jpsModel,
                                                                                      @NotNull ModuleExcludeIndex moduleExcludeIndex,
                                                                                      @NotNull IgnoredFileIndex ignoredFileIndex,
                                                                                      @NotNull BuildDataPaths buildDataPaths)
    {
        List<JALBuildRootDescriptor> rootDescriptors = new ArrayList<>();
        for (JpsModuleSourceRoot roots : this.module.getSourceRoots())
        {
            File rootFile = JpsPathUtil.urlToFile(roots.getUrl());
            if (rootFile.isDirectory())
            {
                JALBuildRootDescriptor rootDescriptor = new JALBuildRootDescriptor(rootFile, this);
                rootDescriptors.add(rootDescriptor);
            }
        }

        return rootDescriptors;
    }

    @Override
    public JALBuildRootDescriptor findRootDescriptor(@NotNull String id,
                                                     @NotNull BuildRootIndex rootIndex)
    {
        Collection<JALBuildRootDescriptor> descriptors = rootIndex.getTargetRoots(this, null);

        for (JALBuildRootDescriptor descriptor : descriptors)
            if (descriptor.getRootId().equals(id))
                return descriptor;

        return null;
    }


}
