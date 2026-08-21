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
                StackEffect stackEffect = stackEffect(instruction);
                int producedCount = stackEffect.producedCount();
                int displayedConsumedCount = countStack(instruction, StackUIElement.DisplayType.POP);
                int currentStackSize = instruction.stack().size();
                int inferredConsumedCount = Math.max(0, previousStackSize + producedCount - currentStackSize);
                int consumedCount = Math.max(stackEffect.consumedCount(), Math.max(displayedConsumedCount, inferredConsumedCount));
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

    private static @NotNull StackEffect stackEffect(@NotNull InstructionUIElement instruction) {
        String instructionName = instruction.instruction().instruction();
        if ("dup".equals(instructionName))
            return new StackEffect(2, 1);

        if (isBinaryStackOperation(instructionName))
            return new StackEffect(1, 2);

        if (isUnaryStackOperation(instructionName))
            return new StackEffect(1, 1);

        if (isArrayLoad(instructionName))
            return new StackEffect(1, 2);

        if (isArrayStore(instructionName))
            return new StackEffect(0, 3);

        return new StackEffect(countStack(instruction, StackUIElement.DisplayType.PUSH), 0);
    }

    private static boolean isBinaryStackOperation(@NotNull String instructionName) {
        return switch (instructionName) {
            case "iadd", "ladd", "fadd", "dadd",
                 "isub", "lsub", "fsub", "dsub",
                 "imul", "lmul", "fmul", "dmul",
                 "idiv", "ldiv", "fdiv", "ddiv",
                 "irem", "lrem", "frem", "drem",
                 "iand", "land", "ior", "lor", "ixor", "lxor",
                 "ishl", "lshl", "ishr", "lshr", "iushr", "lushr",
                 "lcmp", "fcmpl", "fcmpg", "dcmpl", "dcmpg" -> true;
            default -> false;
        };
    }

    private static boolean isUnaryStackOperation(@NotNull String instructionName) {
        return switch (instructionName) {
            case "ineg", "lneg", "fneg", "dneg",
                 "i2l", "i2f", "i2d",
                 "l2i", "l2f", "l2d",
                 "f2i", "f2l", "f2d",
                 "d2i", "d2l", "d2f",
                 "i2b", "i2c", "i2s",
                 "arraylength", "checkcast", "instanceof" -> true;
            default -> false;
        };
    }

    private static boolean isArrayLoad(@NotNull String instructionName) {
        return switch (instructionName) {
            case "iaload", "laload", "faload", "daload", "aaload", "baload", "caload", "saload" -> true;
            default -> false;
        };
    }

    private static boolean isArrayStore(@NotNull String instructionName) {
        return switch (instructionName) {
            case "iastore", "lastore", "fastore", "dastore", "aastore", "bastore", "castore", "sastore" -> true;
            default -> false;
        };
    }

    private record StackEffect(int producedCount, int consumedCount) {
    }
}
