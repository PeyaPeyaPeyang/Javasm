package tokyo.peya.plugin.javasm.compiler;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Label;

public record LabelInfo(
        @NotNull String labelName,
        @NotNull Label label,
        int bytecodeOffset
) {}
