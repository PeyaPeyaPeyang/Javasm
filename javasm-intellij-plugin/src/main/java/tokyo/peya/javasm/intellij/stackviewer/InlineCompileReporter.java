package tokyo.peya.javasm.intellij.stackviewer;

import lombok.AllArgsConstructor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.langjal.compiler.CompileReporter;
import tokyo.peya.langjal.compiler.exceptions.CompileErrorException;

import java.nio.file.Path;
import java.util.function.Consumer;

@AllArgsConstructor
public class InlineCompileReporter implements CompileReporter
{
    private final Consumer<String> messageConsumer;

    @Override
    public void postWarning(@NotNull String message, @Nullable Path sourcePath)
    {
        this.messageConsumer.accept("Warn: " + message);
    }

    @Override
    public void postInfo(@NotNull String message, @Nullable Path sourcePath)
    {
        this.messageConsumer.accept("Info: " + message);
    }

    @Override
    public void postError(@NotNull String message, @Nullable Path sourcePath)
    {
        this.messageConsumer.accept("Error: " + message);
    }

    @Override
    public void postError(@NotNull String message, @NotNull CompileErrorException cause, @Nullable Path sourcePath)
    {
        this.messageConsumer.accept("Error: " + message + " - " + cause.getMessage());
    }

    @Override
    public void postWarning(@NotNull String message, @Nullable Path sourcePath, long line, long column, long length)
    {
        this.messageConsumer.accept("Warn: " + message + " at " + line + ":" + column);
    }

    @Override
    public void postWarning(@NotNull String message, @NotNull Path sourcePath, @NotNull ParserRuleContext ctxt)
    {
        this.messageConsumer.accept(
                "Warn: " + message + " at " + ctxt.getStart().getLine() + ":" + ctxt.getStart().getCharPositionInLine()
        );
    }
}
