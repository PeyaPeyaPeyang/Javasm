package tokyo.peya.javasm.intellij.dependency;

import org.jetbrains.annotations.NotNull;

public record InstructionDependencyEdge(
        @NotNull InstructionDependencyEntry from,
        @NotNull InstructionDependencyEntry to,
        @NotNull InstructionDependencyEdgeKind kind
) {
}
