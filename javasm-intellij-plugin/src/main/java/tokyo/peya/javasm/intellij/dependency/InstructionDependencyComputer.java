package tokyo.peya.javasm.intellij.dependency;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.stackviewer.InstructionUIElement;
import tokyo.peya.javasm.intellij.stackviewer.MethodWrapper;
import tokyo.peya.javasm.intellij.stackviewer.StackFrameAnalysisResult;
import tokyo.peya.javasm.intellij.stackviewer.StackUIElement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class InstructionDependencyComputer {
    private InstructionDependencyComputer() {
    }

    public static @NotNull InstructionDependencyAnalysisResult compute(@NotNull StackFrameAnalysisResult frameAnalysis) {
        Map<InstructionDependencyKind, List<InstructionDependencyEntry>> entries =
                new EnumMap<>(InstructionDependencyKind.class);
        List<InstructionDependencyEdge> edges = new ArrayList<>();
        for (InstructionDependencyKind kind : InstructionDependencyKind.values())
            entries.put(kind, new ArrayList<>());

        for (MethodWrapper method : frameAnalysis.getMethods()) {
            List<InstructionUIElement> instructions = new ArrayList<>(frameAnalysis.getInstructions(method));
            instructions.sort(Comparator.comparingInt(InstructionUIElement::instructionOffset));
            List<InstructionDependencyEntry> stackProducers = new ArrayList<>();
            Set<EdgeEndpoint> dataEdgeEndpoints = new HashSet<>();
            InstructionDependencyEntry previousEntry = null;
            int previousStackSize = 0;

            for (InstructionUIElement instruction : instructions) {
                int producedCount = countStack(instruction, StackUIElement.DisplayType.PUSH);
                int displayedConsumedCount = countStack(instruction, StackUIElement.DisplayType.POP);
                int currentStackSize = instruction.stack().size();
                int inferredConsumedCount = Math.max(0, previousStackSize + producedCount - currentStackSize);
                int consumedCount = Math.max(displayedConsumedCount, inferredConsumedCount);
                previousStackSize = currentStackSize;

                InstructionDependencyKind kind =
                        producedCount > 0 && consumedCount > 0 ? InstructionDependencyKind.BOTH :
                                producedCount > 0 ? InstructionDependencyKind.PRODUCER :
                                        consumedCount > 0 ? InstructionDependencyKind.CONSUMER : InstructionDependencyKind.NEUTRAL;
                InstructionDependencyEntry entry = new InstructionDependencyEntry(
                        method.method().name,
                        method.method().desc,
                        instruction.instructionOffset(),
                        instruction.instruction().instruction(),
                        producedCount,
                        consumedCount,
                        kind
                );
                entries.get(kind).add(entry);

                if (consumedCount > 0) {
                    int available = stackProducers.size();
                    int consumeFrom = Math.max(0, available - consumedCount);
                    List<InstructionDependencyEntry> consumedEntries =
                            new ArrayList<>(stackProducers.subList(consumeFrom, available));
                    stackProducers.subList(consumeFrom, available).clear();
                    for (InstructionDependencyEntry producer : consumedEntries) {
                        edges.add(new InstructionDependencyEdge(producer, entry, InstructionDependencyEdgeKind.DATA));
                        dataEdgeEndpoints.add(new EdgeEndpoint(producer, entry));
                    }
                }

                if (previousEntry != null && !dataEdgeEndpoints.contains(new EdgeEndpoint(previousEntry, entry)))
                    edges.add(new InstructionDependencyEdge(previousEntry, entry, InstructionDependencyEdgeKind.CONTROL));

                for (int i = 0; i < producedCount; i++)
                    stackProducers.add(entry);

                previousEntry = entry;
            }
        }

        return new InstructionDependencyAnalysisResult(entries, edges);
    }

    private static int countStack(@NotNull InstructionUIElement instruction, @NotNull StackUIElement.DisplayType type) {
        return (int) instruction.stack().stream().filter(it -> it.displayType() == type).count();
    }

    private record EdgeEndpoint(@NotNull InstructionDependencyEntry from, @NotNull InstructionDependencyEntry to) {
    }
}
