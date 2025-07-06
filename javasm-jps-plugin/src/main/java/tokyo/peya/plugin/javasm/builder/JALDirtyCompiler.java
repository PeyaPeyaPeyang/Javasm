package tokyo.peya.plugin.javasm.builder;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.ModuleChunk;
import org.jetbrains.jps.builders.DirtyFilesHolder;
import org.jetbrains.jps.builders.java.JavaSourceRootDescriptor;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.incremental.ModuleBuildTarget;
import org.jetbrains.jps.incremental.ModuleLevelBuilder;
import org.jetbrains.jps.incremental.ModuleLevelBuilder.ExitCode;
import org.jetbrains.jps.incremental.ProjectBuildException;
import org.jetbrains.jps.incremental.messages.CompilerMessage;
import org.objectweb.asm.tree.ClassNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;

public class JALDirtyCompiler
{
    public static final String JAL_COMPILER_ID = "JALCompiler";

    private final CompileContext compileContext;
    private final ModuleChunk moduleChunk;
    private final DirtyFilesHolder<JavaSourceRootDescriptor, ModuleBuildTarget> dirtyFilesHolder;
    private final ModuleLevelBuilder.OutputConsumer outputConsumer;

    public JALDirtyCompiler(CompileContext compileContext,
                            ModuleChunk moduleChunk,
                            DirtyFilesHolder<JavaSourceRootDescriptor, ModuleBuildTarget> dirtyFilesHolder,
                            ModuleLevelBuilder.OutputConsumer outputConsumer)
    {
        this.compileContext = compileContext;
        this.moduleChunk = moduleChunk;
        this.dirtyFilesHolder = dirtyFilesHolder;
        this.outputConsumer = outputConsumer;
    }

    public ExitCode run() throws ProjectBuildException, IOException
    {
        Iterator<ModuleBuildTarget> targets = this.moduleChunk.getTargets().iterator();
        ExitCode exitCode = ExitCode.OK;
        while (targets.hasNext())
        {
            ModuleBuildTarget target = targets.next();

            exitCode = buildTarget(target);
            if (exitCode != ExitCode.OK)
                break;
        }

        return exitCode;
    }

    private ExitCode buildTarget(@NotNull ModuleBuildTarget target) throws ProjectBuildException, IOException
    {
        File outputDirFile = target.getOutputDir();
        if (outputDirFile == null)
        {
            this.compileContext.processMessage(new CompilerMessage(
                    JAL_COMPILER_ID,
                CompilerMessage.Kind.ERROR,
                "Output directory is not specified for target: " + target.getModule()
            ));
            return ExitCode.ABORT;
        }
        Path outputDir = outputDirFile.toPath();

        ListMultimap<ModuleBuildTarget, Path> files = ArrayListMultimap.create();
        this.dirtyFilesHolder.processDirtyFiles((modTarget, file, root) -> {
            if (target != modTarget)
                return false;

            if (!file.getName().endsWith(".jal"))
                return false;

            Path filePath = Paths.get(file.getPath());
            files.put(modTarget, filePath);
            return true;
        });

        if (files.isEmpty())
        {
            this.compileContext.processMessage(new CompilerMessage(
                JAL_COMPILER_ID,
                CompilerMessage.Kind.WARNING,
                "No JAL files to compile in target: " + target.getModule()
            ));
            return ExitCode.NOTHING_DONE;
        }

        return this.compileJALFiles(files, outputDir);
    }

    private ExitCode compileJALFiles(
        ListMultimap<ModuleBuildTarget, Path> files,
        Path outputDir
    ) throws ProjectBuildException, IOException
    {
        ExitCode exitCode = ExitCode.OK;
        for (Map.Entry<ModuleBuildTarget, Path> entry : files.entries())
        {
            ModuleBuildTarget target = entry.getKey();
            Path jalFile = entry.getValue();

            JALCompiler compiler = new JALCompiler(target, this.compileContext, this.outputConsumer, jalFile, outputDir);
            ClassNode compiledClass = compiler.compile();
            if (compiledClass == null)
            {
                this.compileContext.processMessage(new CompilerMessage(
                        JAL_COMPILER_ID,
                        CompilerMessage.Kind.ERROR,
                        "Failed to compile JAL file: " + jalFile
                ));
                exitCode = ExitCode.ABORT;
                break;
            }
        }

        return exitCode;
    }
}
