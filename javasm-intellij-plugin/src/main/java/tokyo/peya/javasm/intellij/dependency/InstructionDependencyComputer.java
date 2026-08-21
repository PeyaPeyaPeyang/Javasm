package tokyo.peya.javasm.intellij.dependency;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.IincInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.objectweb.asm.tree.analysis.AnalyzerException;
import org.objectweb.asm.tree.analysis.Frame;
import org.objectweb.asm.tree.analysis.Interpreter;
import org.objectweb.asm.tree.analysis.SourceInterpreter;
import org.objectweb.asm.tree.analysis.SourceValue;
import org.objectweb.asm.tree.analysis.Value;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.stackviewer.InstructionUIElement;
import tokyo.peya.javasm.intellij.stackviewer.MethodWrapper;
import tokyo.peya.javasm.intellij.stackviewer.StackFrameAnalysisResult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static tokyo.peya.langjal.compiler.jvm.EOpcodes.*;

public final class InstructionDependencyComputer {
    private InstructionDependencyComputer() {
    }

    public static @NotNull InstructionDependencyAnalysisResult compute(@NotNull StackFrameAnalysisResult frameAnalysis) {
        Map<InstructionDependencyKind, List<InstructionDependencyEntry>> entries = createEntryMap();
        List<InstructionDependencyEdge> edges = new ArrayList<>();

        for (MethodWrapper method : frameAnalysis.getMethods()) {
            List<InstructionUIElement> instructions = new ArrayList<>(frameAnalysis.getInstructions(method));
            instructions.sort(Comparator.comparingInt(InstructionUIElement::instructionOffset));

            MethodStackTracker stackTracker = new MethodStackTracker(method.method());
            List<AbstractInsnNode> instructionNodes = instructionNodes(method.method());

            for (int i = 0; i < instructions.size(); i++) {
                InstructionUIElement instruction = instructions.get(i);
                AbstractInsnNode instructionNode = i < instructionNodes.size() ? instructionNodes.get(i) : null;
                StackEffect stackEffect = stackTracker.execute(instructionNode);

                InstructionDependencyKind kind = detectKind(stackEffect.producedCount(), stackEffect.consumedCount());
                InstructionDependencyEntry entry = new InstructionDependencyEntry(
                        method.method().name,
                        method.method().desc,
                        instruction.instructionOffset(),
                        instruction.instruction().instruction(),
                        stackEffect.producedCount(),
                        stackEffect.consumedCount(),
                        kind
                );
                entries.get(kind).add(entry);

                stackTracker.commitCurrentProducer(entry);
                stackEffect.addEdgesTo(entry, edges);
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

    private static @NotNull List<AbstractInsnNode> instructionNodes(@NotNull MethodNode method) {
        List<AbstractInsnNode> nodes = new ArrayList<>();
        for (AbstractInsnNode node : method.instructions) {
            if (node.getOpcode() >= 0)
                nodes.add(node);
        }
        return nodes;
    }

    public static boolean isStackRearrangementInstruction(@NotNull String instructionName) {
        int opcode = findOpcode(instructionName);
        return isStackRearrangementOpcode(opcode);
    }

    private static boolean isStackRearrangementOpcode(int opcode) {
        return Opcodes.DUP <= opcode && opcode <= Opcodes.SWAP;
    }

    private static int stackRearrangementInputCount(int opcode, @NotNull Frame<TrackedValue> frame) {
        return switch (opcode) {
            case Opcodes.DUP -> 1;
            case Opcodes.DUP_X1, Opcodes.SWAP -> 2;
            case Opcodes.DUP_X2, Opcodes.DUP2_X1 -> stackSize(frame, 1) == 2 ? 2 : 3;
            case Opcodes.DUP2 -> stackSize(frame, 1) == 2 ? 1 : 2;
            case Opcodes.DUP2_X2 -> dup2X2InputCount(frame);
            default -> 0;
        };
    }

    private static int dup2X2InputCount(@NotNull Frame<TrackedValue> frame) {
        if (stackSize(frame, 1) == 2)
            return stackSize(frame, 2) == 2 ? 2 : 3;
        return stackSize(frame, 3) == 2 ? 3 : 4;
    }

    private static int stackSize(@NotNull Frame<TrackedValue> frame, int fromTop) {
        int index = frame.getStackSize() - fromTop;
        if (index < 0)
            return 1;
        return frame.getStack(index).getSize();
    }

    private record StackEffect(
            int producedCount,
            int consumedCount,
            @NotNull List<TrackedValue> consumedValues
    ) {
        private void addEdgesTo(@NotNull InstructionDependencyEntry consumer,
                                @NotNull List<? super InstructionDependencyEdge> edges) {
            for (TrackedValue value : this.consumedValues) {
                if (value.producer() != null)
                    edges.add(new InstructionDependencyEdge(value.producer(), consumer, InstructionDependencyEdgeKind.DATA));
            }
        }
    }

    private static final class MethodStackTracker {
        private final Frame<TrackedValue> frame;
        private final DependencyInterpreter interpreter;

        private MethodStackTracker(@NotNull MethodNode method) {
            this.frame = new Frame<>(localCapacity(method), stackCapacity(method));
            this.interpreter = new DependencyInterpreter();
            this.initializeLocals(method);
        }

        private @NotNull StackEffect execute(AbstractInsnNode instructionNode) {
            if (instructionNode == null)
                return new StackEffect(0, 0, List.of());

            int stackSizeBefore = this.frame.getStackSize();
            this.interpreter.reset();
            List<TrackedValue> stackConsumerInputs = stackConsumerInputs(instructionNode.getOpcode());
            List<TrackedValue> rearrangedInputs = stackRearrangementInputs(instructionNode.getOpcode());
            try {
                this.frame.execute(instructionNode, this.interpreter);
            } catch (AnalyzerException ignored) {
                return new StackEffect(0, 0, List.of());
            }

            if (!rearrangedInputs.isEmpty())
                this.replaceChangedStackSuffix(stackSizeBefore, rearrangedInputs.size());

            int producedCount = this.countCurrentProducedValues();
            List<TrackedValue> consumedValues;
            if (!stackConsumerInputs.isEmpty())
                consumedValues = stackConsumerInputs;
            else if (!rearrangedInputs.isEmpty())
                consumedValues = rearrangedInputs;
            else
                consumedValues = this.interpreter.consumedValues();
            return new StackEffect(producedCount, consumedValues.size(), consumedValues);
        }

        private void commitCurrentProducer(@NotNull InstructionDependencyEntry entry) {
            for (int i = 0; i < this.frame.getStackSize(); i++) {
                TrackedValue value = this.frame.getStack(i);
                if (value.isCurrentProducer())
                    this.frame.setStack(i, value.withProducer(entry));
            }
        }

        private void initializeLocals(@NotNull MethodNode method) {
            int localIndex = 0;
            if ((method.access & Opcodes.ACC_STATIC) == 0)
                localIndex = this.setLocal(localIndex, TrackedValue.unknown(1));

            for (Type argumentType : Type.getArgumentTypes(method.desc)) {
                int size = argumentType.getSize();
                localIndex = this.setLocal(localIndex, TrackedValue.unknown(size));
                if (size == 2)
                    localIndex = this.setLocal(localIndex, TrackedValue.unknown(1));
            }

            while (localIndex < this.frame.getLocals())
                localIndex = this.setLocal(localIndex, TrackedValue.unknown(1));
        }

        private int setLocal(int localIndex, @NotNull TrackedValue value) {
            if (localIndex < this.frame.getLocals())
                this.frame.setLocal(localIndex, value);
            return localIndex + 1;
        }

        private @NotNull List<TrackedValue> stackRearrangementInputs(int opcode) {
            int inputCount = stackRearrangementInputCount(opcode, this.frame);
            if (inputCount == 0)
                return List.of();

            return this.topStackValues(inputCount);
        }

        private @NotNull List<TrackedValue> stackConsumerInputs(int opcode) {
            int inputCount = switch (opcode) {
                case Opcodes.POP -> 1;
                case Opcodes.POP2 -> stackSize(this.frame, 1) == 2 ? 1 : 2;
                default -> 0;
            };
            if (inputCount == 0)
                return List.of();

            return this.topStackValues(inputCount);
        }

        private @NotNull List<TrackedValue> topStackValues(int inputCount) {
            List<TrackedValue> values = new ArrayList<>();
            int stackSize = this.frame.getStackSize();
            for (int i = Math.max(0, stackSize - inputCount); i < stackSize; i++)
                values.add(this.frame.getStack(i));
            return values;
        }

        private void replaceChangedStackSuffix(int stackSizeBefore, int inputCount) {
            int start = Math.max(0, stackSizeBefore - inputCount);
            for (int i = start; i < this.frame.getStackSize(); i++)
                this.frame.setStack(i, TrackedValue.current(this.frame.getStack(i).getSize()));
        }

        private int countCurrentProducedValues() {
            int count = 0;
            for (int i = 0; i < this.frame.getStackSize(); i++) {
                if (this.frame.getStack(i).isCurrentProducer())
                    count++;
            }
            return count;
        }

        private static int localCapacity(@NotNull MethodNode method) {
            int capacity = argumentLocalCount(method);
            for (AbstractInsnNode node : method.instructions) {
                if (node instanceof VarInsnNode varInsnNode)
                    capacity = Math.max(capacity, varInsnNode.var + variableSize(varInsnNode.getOpcode()));
                else if (node instanceof IincInsnNode iincInsnNode)
                    capacity = Math.max(capacity, iincInsnNode.var + 1);
            }
            return Math.max(method.maxLocals, capacity);
        }

        private static int argumentLocalCount(@NotNull MethodNode method) {
            int count = (method.access & Opcodes.ACC_STATIC) == 0 ? 1 : 0;
            for (Type argumentType : Type.getArgumentTypes(method.desc))
                count += argumentType.getSize();
            return count;
        }

        private static int variableSize(int opcode) {
            return opcode == Opcodes.LLOAD || opcode == Opcodes.DLOAD
                    || opcode == Opcodes.LSTORE || opcode == Opcodes.DSTORE
                    ? 2
                    : 1;
        }

        private static int stackCapacity(@NotNull MethodNode method) {
            int minimumCapacity = Math.max(8, method.instructions.size() + argumentLocalCount(method));
            return Math.max(method.maxStack, minimumCapacity);
        }
    }

    private static final class DependencyInterpreter extends Interpreter<TrackedValue> {
        private final SourceInterpreter delegate;
        private final List<TrackedValue> consumedValues;

        private DependencyInterpreter() {
            super(Opcodes.ASM9);
            this.delegate = new SourceInterpreter();
            this.consumedValues = new ArrayList<>();
        }

        private void reset() {
            this.consumedValues.clear();
        }

        private @NotNull List<TrackedValue> consumedValues() {
            return List.copyOf(this.consumedValues);
        }

        @Override
        public TrackedValue newValue(Type type) {
            return toUnknown(this.delegate.newValue(type));
        }

        @Override
        public TrackedValue newOperation(AbstractInsnNode insn) {
            return toCurrent(this.delegate.newOperation(insn));
        }

        @Override
        public TrackedValue copyOperation(AbstractInsnNode insn, TrackedValue value) {
            if (isStoreOpcode(insn.getOpcode()))
                this.consume(value);
            return toCurrent(this.delegate.copyOperation(insn, new SourceValue(value.getSize())), value.getSize());
        }

        @Override
        public TrackedValue unaryOperation(AbstractInsnNode insn, TrackedValue value) {
            this.consume(value);
            return toCurrent(this.delegate.unaryOperation(insn, new SourceValue(value.getSize())));
        }

        @Override
        public TrackedValue binaryOperation(
                AbstractInsnNode insn,
                TrackedValue value1,
                TrackedValue value2
        ) {
            this.consume(value1);
            this.consume(value2);
            return toCurrent(this.delegate.binaryOperation(
                    insn,
                    new SourceValue(value1.getSize()),
                    new SourceValue(value2.getSize())
            ));
        }

        @Override
        public TrackedValue ternaryOperation(
                AbstractInsnNode insn,
                TrackedValue value1,
                TrackedValue value2,
                TrackedValue value3
        ) {
            this.consume(value1);
            this.consume(value2);
            this.consume(value3);
            return toCurrent(this.delegate.ternaryOperation(
                    insn,
                    new SourceValue(value1.getSize()),
                    new SourceValue(value2.getSize()),
                    new SourceValue(value3.getSize())
            ));
        }

        @Override
        public TrackedValue naryOperation(
                AbstractInsnNode insn,
                List<? extends TrackedValue> values
        ) {
            for (TrackedValue value : values)
                this.consume(value);
            return toCurrent(this.delegate.naryOperation(insn, sourceValues(values)));
        }

        @Override
        public void returnOperation(AbstractInsnNode insn, TrackedValue value, TrackedValue expected) {
            this.consume(value);
            if (value != null && expected != null)
                this.delegate.returnOperation(insn, new SourceValue(value.getSize()), new SourceValue(expected.getSize()));
        }

        @Override
        public TrackedValue merge(TrackedValue value1, TrackedValue value2) {
            return value1;
        }

        private void consume(TrackedValue value) {
            if (value != null)
                this.consumedValues.add(value);
        }

        private static boolean isStoreOpcode(int opcode) {
            return Opcodes.ISTORE <= opcode && opcode <= Opcodes.ASTORE;
        }

        private static TrackedValue toUnknown(SourceValue value) {
            return value == null ? null : TrackedValue.unknown(value.getSize());
        }

        private static TrackedValue toCurrent(SourceValue value) {
            return value == null ? null : TrackedValue.current(value.getSize());
        }

        private static TrackedValue toCurrent(SourceValue value, int fallbackSize) {
            return value == null ? null : TrackedValue.current(value.getSize() == 0 ? fallbackSize : value.getSize());
        }

        private static @NotNull List<SourceValue> sourceValues(@NotNull List<? extends TrackedValue> trackedValues) {
            List<SourceValue> values = new ArrayList<>();
            for (TrackedValue value : trackedValues)
                values.add(new SourceValue(value.getSize()));
            return values;
        }
    }

    private record TrackedValue(int size, InstructionDependencyEntry producer, boolean currentProducer) implements Value {
        private static @NotNull TrackedValue unknown(int size) {
            return new TrackedValue(size, null, false);
        }

        private static @NotNull TrackedValue current(int size) {
            return new TrackedValue(size, null, true);
        }

        @Override
        public int getSize() {
            return this.size;
        }

        private boolean isCurrentProducer() {
            return this.currentProducer;
        }

        private @NotNull TrackedValue withProducer(@NotNull InstructionDependencyEntry producer) {
            return new TrackedValue(this.size, producer, false);
        }
    }
}
