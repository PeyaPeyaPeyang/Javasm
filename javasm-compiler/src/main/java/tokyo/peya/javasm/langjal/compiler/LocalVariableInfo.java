package tokyo.peya.javasm.langjal.compiler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;

public record LocalVariableInfo(
        @NotNull
        String name,
        @NotNull
        TypeDescriptor type,
        @Nullable
        LabelInfo start,
        @Nullable
        LabelInfo end,
        int index,
        boolean isParameter
)
{
    public LocalVariableInfo(@NotNull String name, @NotNull TypeDescriptor type,
                             @Nullable LabelInfo start, @Nullable LabelInfo end, int index)
    {
        this(name, type, start, end, index, false);
    }
}
