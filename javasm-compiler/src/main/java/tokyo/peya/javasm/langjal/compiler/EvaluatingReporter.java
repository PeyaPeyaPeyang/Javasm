package tokyo.peya.javasm.langjal.compiler;

import org.antlr.v4.runtime.ParserRuleContext;
import org.jetbrains.annotations.NotNull;

public interface EvaluatingReporter
{
    void postError(@NotNull String message);

    void postWarning(@NotNull String message);

    void postInfo(@NotNull String message);

    void postError(@NotNull String message, @NotNull Throwable cause);

    void postError(@NotNull String message, @NotNull Throwable cause, long line, long column, long length);

    void postWarning(@NotNull String message, long line, long column, long length);

    void postWarning(@NotNull String message, @NotNull ParserRuleContext ctxt);
}
