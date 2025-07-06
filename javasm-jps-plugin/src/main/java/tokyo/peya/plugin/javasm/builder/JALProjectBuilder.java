package tokyo.peya.plugin.javasm.builder;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.ModuleChunk;
import org.jetbrains.jps.builders.DirtyFilesHolder;
import org.jetbrains.jps.builders.java.JavaSourceRootDescriptor;
import org.jetbrains.jps.incremental.BuilderCategory;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.incremental.ModuleBuildTarget;
import org.jetbrains.jps.incremental.ModuleLevelBuilder;
import org.jetbrains.jps.incremental.ProjectBuildException;

import java.io.IOException;
import java.util.List;

public class JALProjectBuilder extends ModuleLevelBuilder
{
    public JALProjectBuilder()
    {
        super(BuilderCategory.TRANSLATOR);
    }

    
    @Override
    public ExitCode build(CompileContext compileContext,
                          ModuleChunk moduleChunk,
                          DirtyFilesHolder<JavaSourceRootDescriptor, ModuleBuildTarget> dirtyFilesHolder,
                          OutputConsumer outputConsumer) throws ProjectBuildException, IOException
    {
        JALDirtyCompiler compiler = new JALDirtyCompiler(compileContext, moduleChunk, dirtyFilesHolder, outputConsumer);
        return compiler.run();
    }

    @Override
    public @NotNull List<String> getCompilableFileExtensions()
    {
        return List.of("jal");
    }

    @Override
    public @NotNull @Nls(capitalization = Nls.Capitalization.Sentence) String getPresentableName()
    {
        return "Java assembly language builder";
    }
}
