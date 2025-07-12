package tokyo.peya.javasm.intellij.builder;

import lombok.AllArgsConstructor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.incremental.messages.BuildMessage;
import org.jetbrains.jps.incremental.messages.CompilerMessage;
import tokyo.peya.javasm.langjal.compiler.CompileReporter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

@AllArgsConstructor
public class JALCompileReporterImpl implements CompileReporter
{
    public static final String COMPILER_NAME = "JavaSM JAL Compiler";

    private final CompileContext compileContext;

    @Override
    public void postError(@NotNull String message, @NotNull Path sourcePath)
    {
        this.compileContext.processMessage(
                new CompilerMessage(
                        COMPILER_NAME,
                        BuildMessage.Kind.ERROR,
                        message,
                        sourcePath.toString()
                )
        );
    }

    @Override
    public void postWarning(@NotNull String message, @NotNull Path sourcePath)
    {
        this.compileContext.processMessage(
                new CompilerMessage(
                        COMPILER_NAME,
                        BuildMessage.Kind.WARNING,
                        message,
                        sourcePath.toString()
                )
        );
    }

    @Override
    public void postInfo(@NotNull String message, @NotNull Path sourcePath)
    {
        this.compileContext.processMessage(
                new CompilerMessage(
                        COMPILER_NAME,
                        BuildMessage.Kind.INFO,
                        message,
                        sourcePath.toString()
                )
        );
    }

    @Override
    public void postError(@NotNull String message, @NotNull Throwable cause, @NotNull Path sourcePath)
    {
        this.postError(message, cause, sourcePath, -1, -1, -1);
    }

    @Override
    public void postError(@NotNull String message, @NotNull Throwable e, @NotNull Path sourcePath, long line,
                          long column, long length)
    {
        this.postOnLine(message, sourcePath, BuildMessage.Kind.ERROR, line, column, length);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(outputStream));
        this.compileContext.processMessage(
                new CompilerMessage(
                        COMPILER_NAME,
                        BuildMessage.Kind.ERROR,
                        outputStream.toString(),
                        sourcePath.toString()
                )
        );
    }

    @Override
    public void postWarning(@NotNull String message, @NotNull Path sourcePath, long line, long column, long length)
    {
        this.postOnLine(message, sourcePath, BuildMessage.Kind.WARNING, line, column, length);
    }

    @Override
    public void postWarning(@NotNull String message, @NotNull Path sourcePath, @NotNull ParserRuleContext ctxt)
    {
        long line = ctxt.getStart().getLine();
        long column = ctxt.getStart().getCharPositionInLine() + 1; // ANTLRは0始まりなので+1
        long length = ctxt.getStop().getStopIndex() - ctxt.getStart().getStartIndex() + 1;
        this.postOnLine(message, sourcePath, BuildMessage.Kind.WARNING, line, column, length);
    }

    private void postOnLine(
            @NotNull String message,
            @NotNull Path sourcePath,
            @NotNull BuildMessage.Kind kind,
            long line,
            long column,
            long length
    )
    {
        long problemBeginOffset = -1;
        long problemEndOffset = -1;
        long problemLocationOffset = -1;
        long locationLine = -1;
        long locationColumn = -1;
        try
        {
            String content = Files.readString(sourcePath);
            long offset = getOffset(content, line, column);
            problemBeginOffset = offset;
            problemEndOffset = offset + length;
            problemLocationOffset = offset;
        }
        catch (IOException ignored)
        {

        }

        this.compileContext.processMessage(
                new CompilerMessage(
                        COMPILER_NAME,
                        kind,
                        message,
                        sourcePath.toString(),
                        problemBeginOffset,
                        problemEndOffset,
                        problemLocationOffset,
                        locationLine,
                        locationColumn
                )
        );
    }

    private static long getOffset(String text, long line, long column)
    {
        String[] lines = text.split("\n", -1);  // 最後の空行も含める
        long offset = 0;
        for (int i = 0; i < line - 1 && i < lines.length; i++)
            offset += lines[i].length() + 1; // +1 -> 改行

        offset += column - 1; // column は 1始まりだから -1
        return offset;
    }
}
