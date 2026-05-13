package tokyo.peya.javasm.intellij.dependency;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public record InstructionDependencyAnalysisResult(
        @NotNull Map<InstructionDependencyKind, List<InstructionDependencyEntry>> entries,
        @NotNull List<InstructionDependencyEdge> edges,
        @NotNull List<InstructionSetDependencyGroup> instructionSets,
        @NotNull List<InstructionSetJumpEdge> instructionSetJumps
) {
    public static InstructionDependencyAnalysisResult empty() {
        Map<InstructionDependencyKind, List<InstructionDependencyEntry>> map =
                new EnumMap<>(InstructionDependencyKind.class);
        for (InstructionDependencyKind kind : InstructionDependencyKind.values())
            map.put(kind, List.of());
        return new InstructionDependencyAnalysisResult(map, List.of(), List.of(), List.of());
    }

    public InstructionDependencyAnalysisResult(
            @NotNull Map<InstructionDependencyKind, List<InstructionDependencyEntry>> entries,
            @NotNull List<InstructionDependencyEdge> edges
    ) {
        this(entries, edges, List.of(), List.of());
    }

    public @NotNull InstructionDependencyAnalysisResult withInstructionSets(
            @NotNull List<InstructionSetDependencyGroup> instructionSets,
            @NotNull List<InstructionSetJumpEdge> instructionSetJumps
    ) {
        return new InstructionDependencyAnalysisResult(this.entries, this.edges, instructionSets, instructionSetJumps);
    }

    public @NotNull Collection<InstructionDependencyEntry> getEntries(@NotNull InstructionDependencyKind kind) {
        return this.entries.getOrDefault(kind, List.of());
    }

    public @NotNull List<InstructionDependencyEntry> getAllEntries() {
        List<InstructionDependencyEntry> allEntries = new ArrayList<>();
        for (InstructionDependencyKind kind : InstructionDependencyKind.values())
            allEntries.addAll(this.getEntries(kind));
        return allEntries;
    }
}
