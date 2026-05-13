package tokyo.peya.javasm.intellij.dependency;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public record InstructionSetDependencyGroup(
        @NotNull String methodName,
        @NotNull String methodDescriptor,
        @NotNull String label,
        int index,
        @NotNull List<Integer> instructionOffsets
) {
}
