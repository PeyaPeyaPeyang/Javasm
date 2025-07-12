package tokyo.peya.javasm.langjal.compiler;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JALFileCompiler
{
    private final CompileReporter reporter;
    private final Path outputDir;

    public JALFileCompiler(@NotNull CompileReporter reporter, @NotNull Path outputDir) throws IOException
    {
        this.reporter = reporter;
        this.outputDir = outputDir;

        // Ensure the output directory exists
        if (!Files.exists(outputDir))
            Files.createDirectories(outputDir);
    }

    @Nullable
    public ClassNode compile(@NotNull Path inputFile) throws IOException
    {
        CharStream charStream = CharStreams.fromPath(inputFile);
        JALLexer lexer = new JALLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        JALParser parser = new JALParser(tokenStream);
        JALCompileErrorStrategy errorStrategy = new JALCompileErrorStrategy(this.reporter, inputFile);
        parser.setErrorHandler(errorStrategy);

        FileEvaluatingReporter fileReporter = new FileEvaluatingReporter(this.reporter, inputFile);
        fileReporter.postInfo("Compiling JAL file: " + inputFile.toAbsolutePath());

        JALParser.RootContext tree = parser.root();
        if (errorStrategy.isError())
            return null;

        JALParser.ClassDefinitionContext classDefinition = tree.classDefinition();
        if (classDefinition == null)
            return new ClassNode();

        ClassNode evaluatedClass = JALClassCompiler.evaluateClassAST(
                fileReporter,
                classDefinition
        );
        this.writeClass(fileReporter, evaluatedClass);

        return evaluatedClass;
    }

    private void writeClass(@NotNull FileEvaluatingReporter reporter, @NotNull ClassNode classNode)
    {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);

        Path outputFile = this.outputDir.resolve(classNode.name + ".class");
        try
        {
            classNode.accept(classWriter);
            Files.createDirectories(outputFile.getParent());

            Files.write(outputFile, classWriter.toByteArray());
            reporter.postInfo("Class file written: " + outputFile.toAbsolutePath());

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
        catch (Exception e)
        {
            reporter.postError("Failed to write class file: " + e.getMessage(), e);
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
