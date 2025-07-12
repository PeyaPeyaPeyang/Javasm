package tokyo.peya.javasm.langjal.compiler;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import tokyo.peya.javasm.langjal.compiler.exceptions.ClassFinalisingException;
import tokyo.peya.javasm.langjal.compiler.exceptions.ClassWritingException;
import tokyo.peya.javasm.langjal.compiler.exceptions.CompileErrorException;
import tokyo.peya.javasm.langjal.compiler.exceptions.FileReadingException;

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
    public ClassNode compile(@NotNull Path inputFile) throws CompileErrorException
    {
        CharStream charStream;
        try
        {
            charStream = CharStreams.fromPath(inputFile);
        }
        catch (IOException e)
        {
            this.reporter.postError(
                    "Failed to read input file: " + inputFile.toAbsolutePath(),
                    new FileReadingException(e, inputFile),
                    inputFile
            );
            return null;
        }

        return this.compile(charStream, inputFile);
    }

    @NotNull
    public ClassNode compile(@NotNull String sourceCode) throws CompileErrorException
    {
        return this.compile(CharStreams.fromString(sourceCode), null);
    }

    @NotNull
    private ClassNode compile(@NotNull CharStream charStream, @Nullable Path sourcePath) throws CompileErrorException
    {
        JALLexer lexer = new JALLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        JALParser parser = new JALParser(tokenStream);
        JALCompileErrorStrategy errorStrategy = new JALCompileErrorStrategy(this.reporter, sourcePath);
        parser.setErrorHandler(errorStrategy);

        FileEvaluatingReporter fileReporter = new FileEvaluatingReporter(this.reporter, sourcePath);
        fileReporter.postInfo("Compiling JAL source code");

        JALParser.RootContext tree = parser.root();
        if (errorStrategy.isError())
            return new ClassNode();

        JALParser.ClassDefinitionContext classDefinition = tree.classDefinition();
        if (classDefinition == null)
            return new ClassNode();

        JALClassCompiler classCompiler = new JALClassCompiler(fileReporter);
        ClassNode evaluatedClass = classCompiler.compileClassAST(classDefinition);
        this.writeClass(fileReporter, evaluatedClass);

        return evaluatedClass;
    }

    private void writeClass(@NotNull FileEvaluatingReporter reporter,
                            @NotNull ClassNode classNode) throws CompileErrorException
    {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        try
        {

            classNode.accept(classWriter);
        }
        catch (Throwable e)
        {
            throw new ClassFinalisingException(e);
        }

        Path outputFile = this.outputDir.resolve(classNode.name + ".class");
        try
        {
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
        catch (IOException e)
        {
            throw new ClassWritingException(e);
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
