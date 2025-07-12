package tokyo.peya.javasm.langjal.compiler;

import org.antlr.v4.runtime.ParserRuleContext;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public interface CompileReporter
{
    void postWarning(@NotNull String message, @NotNull Path sourcePath);

    void postInfo(@NotNull String message, @NotNull Path sourcePath);

    void postError(@NotNull String message, @NotNull Path sourcePath);

    void postError(@NotNull String message, @NotNull Throwable cause, @NotNull Path sourcePath);

    void postError(@NotNull String message, @NotNull Throwable cause, @NotNull Path sourcePath, long line, long column,
                   long length);

    void postWarning(@NotNull String message, @NotNull Path sourcePath, long line, long column, long length);

    void postWarning(@NotNull String message, @NotNull Path sourcePath, @NotNull ParserRuleContext ctxt);
}
