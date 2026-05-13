package tokyo.peya.javasm.intellij.dependency;

import org.jetbrains.annotations.NotNull;

public record InstructionDependencyEntry(
        @NotNull String methodName,
        @NotNull String methodDescriptor,
        int instructionOffset,
        @NotNull String instructionName,
        int producedCount,
        int consumedCount,
        @NotNull InstructionDependencyKind kind
) {
}
