package tokyo.peya.plugin.javasm.compiler;

import org.jetbrains.annotations.NotNull;

public record LocalVariableInfo(
    @NotNull
    String name,
    @NotNull
    String type,
    int index
)
{
    public LocalVariableInfo(int index, @NotNull String type) {
        this(String.format("var%5d", index), type, index);
    }
}
