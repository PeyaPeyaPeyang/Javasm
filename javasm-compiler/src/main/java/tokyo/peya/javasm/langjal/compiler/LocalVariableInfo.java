package tokyo.peya.javasm.langjal.compiler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record LocalVariableInfo(
        @NotNull
        String name,
        @NotNull
        String type,
        @Nullable
        LabelInfo start,
        @Nullable
        LabelInfo end,
        int index,
        boolean isParameter
)
{
    public LocalVariableInfo(String name, String type, int index)
    {
        this(name, type, null, null, index, false);
    }

    public LocalVariableInfo(int index, @NotNull String type)
    {
        this(String.format("var%05d", index), type, index);
    }
}
