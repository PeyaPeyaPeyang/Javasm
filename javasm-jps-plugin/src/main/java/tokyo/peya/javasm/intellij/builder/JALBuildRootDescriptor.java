package tokyo.peya.javasm.intellij.builder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.builders.BuildRootDescriptor;
import org.jetbrains.jps.builders.BuildTarget;

import java.io.File;

public class JALBuildRootDescriptor extends BuildRootDescriptor
{

    private final File rootFile;
    private final JALBuildTarget target;

    public JALBuildRootDescriptor(File rootFile, JALBuildTarget target)
    {
        this.rootFile = rootFile;
        this.target = target;
    }

    @Override
    public @NotNull String getRootId()
    {
        return this.rootFile.getAbsolutePath();
    }

    @Override
    public @NotNull File getRootFile()
    {
        return this.rootFile;
    }

    @Override
    public @NotNull BuildTarget<?> getTarget()
    {
        return this.target;
    }
}
