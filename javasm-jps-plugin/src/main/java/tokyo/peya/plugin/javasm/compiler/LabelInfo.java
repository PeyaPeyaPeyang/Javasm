package tokyo.peya.plugin.javasm.compiler;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Label;

public record LabelInfo(
        @NotNull String name,
        @NotNull Label label,
        int instructionIndex
) {}
