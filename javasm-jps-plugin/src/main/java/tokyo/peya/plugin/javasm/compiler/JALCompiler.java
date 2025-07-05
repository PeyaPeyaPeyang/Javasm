package tokyo.peya.plugin.javasm.compiler;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.incremental.BinaryContent;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.incremental.CompiledClass;
import org.jetbrains.jps.incremental.ModuleBuildTarget;
import org.jetbrains.jps.incremental.ModuleLevelBuilder;
import org.jetbrains.jps.incremental.messages.BuildMessage;
import org.jetbrains.jps.incremental.messages.CompilerMessage;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import tokyo.peya.plugin.javasm.langjal.compiler.JALLexer;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JALCompiler
{
    private final JALLexer lexer;
    private final CommonTokenStream tokenStream;
    private final JALParser parser;
    private final JALCompileErrorStrategy errorStrategy;

    private final ModuleBuildTarget target;
    private final CompileContext ctxt;
    private final ModuleLevelBuilder.OutputConsumer outputConsumer;
    private final Path file;
    private final Path outputDir;

    public JALCompiler(@NotNull ModuleBuildTarget target, @NotNull CompileContext ctxt,
                       @NotNull ModuleLevelBuilder.OutputConsumer outputConsumer,
                       @NotNull Path inputFile, @NotNull Path outputDir) throws IOException
    {
        CharStream charStream = CharStreams.fromPath(inputFile);

        this.target = target;
        this.ctxt = ctxt;
        this.outputConsumer = outputConsumer;
        this.file = inputFile;
        this.outputDir = outputDir;


        this.lexer = new JALLexer(charStream);
        this.tokenStream = new CommonTokenStream(this.lexer);
        this.parser = new JALParser(this.tokenStream);
        this.parser.setErrorHandler(this.errorStrategy = new JALCompileErrorStrategy(ctxt, inputFile));
    }

    @Nullable
    public ClassNode compile()
    {
        this.ctxt.processMessage(new CompilerMessage(
                "JALCompiler",
                BuildMessage.Kind.INFO,
                "Compiling JAL file: " + this.file.toAbsolutePath()
        ));

        JALParser.RootContext tree = this.parser.root();
        if (this.errorStrategy.isError())
            return null;

        this.ctxt.processMessage(new CompilerMessage(
                "JALCompiler",
                BuildMessage.Kind.INFO,
                "AST tree recognised successfully for: " + this.file.toAbsolutePath()
        ));

        JALParser.ClassDefinitionContext classDefinition = tree.classDefinition();
        if (classDefinition == null)
            return new ClassNode();

        ClassNode evaluatedClass = JALClassEvaluator.evaluateClassAST(classDefinition);
        this.writeClass(evaluatedClass);

        return evaluatedClass;
    }

    private void writeClass(@NotNull ClassNode classNode)
    {
        ClassWriter classWriter = new ClassWriter(
                ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS
        );
        classNode.accept(classWriter);

        Path outputFile = this.outputDir.resolve(classNode.name + ".class");
        try
        {
            Files.createDirectories(outputFile.getParent());

            BinaryContent binaryContent = new BinaryContent(classWriter.toByteArray());
            Files.write(outputFile, binaryContent.toByteArray());
            this.ctxt.processMessage(new CompilerMessage(
                    "JALCompiler",
                    BuildMessage.Kind.INFO,
                    "Class written to: " + outputFile.toAbsolutePath()
            ));

            /*
            this.outputConsumer.registerCompiledClass(
                    this.target,
                    new CompiledClass(
                            this.file.toFile(),
                            outputFile.toFile(),
                            toClassName(classNode.name),
                            binaryContent
                    )
            );*/
        }
        catch (IOException e)
        {
            this.ctxt.processMessage(new CompilerMessage(
                    "JALCompiler",
                    BuildMessage.Kind.ERROR,
                    "Failed to write class file: " + e.getMessage()
            ));
        }
    }

    private static String toClassName(@NotNull String fullQualifiedName)
    {
        // a/b/c -> c
        int lastSlashIndex = fullQualifiedName.lastIndexOf('/');
        if (lastSlashIndex == -1)
            return fullQualifiedName;
        else
            return fullQualifiedName.substring(lastSlashIndex + 1);
    }
}
