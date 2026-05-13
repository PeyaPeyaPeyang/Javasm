package tokyo.peya.javasm.intellij.dependency;

import org.jetbrains.annotations.NotNull;

public record InstructionSetJumpEdge(
        @NotNull InstructionDependencyEntry from,
        @NotNull InstructionSetDependencyGroup to
) {
}
