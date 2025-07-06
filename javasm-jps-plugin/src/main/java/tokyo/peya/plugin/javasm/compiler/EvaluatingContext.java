package tokyo.peya.plugin.javasm.compiler;

import org.jetbrains.annotations.NotNull;

public interface EvaluatingContext
{
    void postError(@NotNull String message);
    void postWarning(@NotNull String message);
    void postInfo(@NotNull String message);

    void postError(@NotNull String message, @NotNull Throwable cause);
    void postError(@NotNull String message, @NotNull Throwable cause, long line, long column, long length);

    void postWarning(@NotNull String message, long line, long column, long length);
}
