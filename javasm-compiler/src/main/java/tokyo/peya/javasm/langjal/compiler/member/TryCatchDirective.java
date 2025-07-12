package tokyo.peya.javasm.langjal.compiler.member;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record TryCatchDirective(
        @NotNull
        LabelInfo tryBlockStartLabel,
        @NotNull
        LabelInfo tryBlockEndLabel,
        @Nullable
        LabelInfo catchBlockLabel,
        @Nullable
        String exceptionType,
        @Nullable
        LabelInfo finallyBlockLabel
)
{
}
