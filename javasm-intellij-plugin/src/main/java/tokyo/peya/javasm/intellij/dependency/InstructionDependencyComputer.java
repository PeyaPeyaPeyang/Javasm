package tokyo.peya.javasm.intellij.dependency;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.stackviewer.InstructionUIElement;
import tokyo.peya.javasm.intellij.stackviewer.MethodWrapper;
import tokyo.peya.javasm.intellij.stackviewer.StackFrameAnalysisResult;
import tokyo.peya.javasm.intellij.stackviewer.StackUIElement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class InstructionDependencyComputer {
    private InstructionDependencyComputer() {
    }

    public static @NotNull InstructionDependencyAnalysisResult compute(@NotNull StackFrameAnalysisResult frameAnalysis) {
        Map<InstructionDependencyKind, List<InstructionDependencyEntry>> entries = createEntryMap();
        List<InstructionDependencyEdge> edges = new ArrayList<>();

        for (MethodWrapper method : frameAnalysis.getMethods()) {
            List<InstructionUIElement> instructions = new ArrayList<>(frameAnalysis.getInstructions(method));
            instructions.sort(Comparator.comparingInt(InstructionUIElement::instructionOffset));

            List<InstructionDependencyEntry> stackProducers = new ArrayList<>();
            int previousStackSize = 0;

            for (InstructionUIElement instruction : instructions) {
                int producedCount = countStack(instruction, StackUIElement.DisplayType.PUSH);
                int displayedConsumedCount = countStack(instruction, StackUIElement.DisplayType.POP);
                int currentStackSize = instruction.stack().size();
                int inferredConsumedCount = Math.max(0, previousStackSize + producedCount - currentStackSize);
                int consumedCount = Math.max(displayedConsumedCount, inferredConsumedCount);
                previousStackSize = currentStackSize;

                InstructionDependencyKind kind = detectKind(producedCount, consumedCount);
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
                    consumeFromStack(stackProducers, entry, consumedCount, edges);
                }

                pushProducedEntries(stackProducers, entry, producedCount);
            }
        }

        return new InstructionDependencyAnalysisResult(entries, edges);
    }

    private static @NotNull Map<InstructionDependencyKind, List<InstructionDependencyEntry>> createEntryMap() {
        Map<InstructionDependencyKind, List<InstructionDependencyEntry>> entries =
                new EnumMap<>(InstructionDependencyKind.class);
        for (InstructionDependencyKind kind : InstructionDependencyKind.values()) {
            entries.put(kind, new ArrayList<>());
        }
        return entries;
    }

    private static @NotNull InstructionDependencyKind detectKind(int producedCount, int consumedCount) {
        if (producedCount > 0 && consumedCount > 0) {
            return InstructionDependencyKind.BOTH;
        }
        if (producedCount > 0) {
            return InstructionDependencyKind.PRODUCER;
        }
        if (consumedCount > 0) {
            return InstructionDependencyKind.CONSUMER;
        }
        return InstructionDependencyKind.NEUTRAL;
    }

    private static void consumeFromStack(
            @NotNull List<InstructionDependencyEntry> stackProducers,
            @NotNull InstructionDependencyEntry consumer,
            int consumedCount,
            @NotNull List<InstructionDependencyEdge> edges
    ) {
        int available = stackProducers.size();
        int consumeFrom = Math.max(0, available - consumedCount);
        List<InstructionDependencyEntry> consumedEntries =
                new ArrayList<>(stackProducers.subList(consumeFrom, available));
        stackProducers.subList(consumeFrom, available).clear();
        for (InstructionDependencyEntry producer : consumedEntries) {
            edges.add(new InstructionDependencyEdge(producer, consumer, InstructionDependencyEdgeKind.DATA));
        }
    }

    private static void pushProducedEntries(
            @NotNull List<InstructionDependencyEntry> stackProducers,
            @NotNull InstructionDependencyEntry producer,
            int producedCount
    ) {
        for (int i = 0; i < producedCount; i++) {
            stackProducers.add(producer);
        }
    }

    private static int countStack(@NotNull InstructionUIElement instruction, @NotNull StackUIElement.DisplayType type) {
        return (int) instruction.stack().stream().filter(it -> it.displayType() == type).count();
    }
}
