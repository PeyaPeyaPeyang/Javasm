package tokyo.peya.javasm.langjal.compiler.analyser;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.LocalStackElement;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.NullElement;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.ObjectElement;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.PrimitiveElement;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElement;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementCapsule;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.TopElement;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.UninitializedElement;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;
import tokyo.peya.javasm.langjal.compiler.member.LabelInfo;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FrameDifferenceInfo
{
    private static final FrameDifferenceInfo SAME = new FrameDifferenceInfo(
            null,
            new StackElement[0],
            new LocalStackElement[0],
            new StackElement[0],
            new LocalStackElement[0]
    );

    @Nullable
    private final LabelInfo label;

    @NotNull
    private final StackElement[] additionalStack;
    @NotNull
    private final LocalStackElement[] additionalLocals;

    @NotNull
    private final StackElement[] consumedStack;
    @NotNull
    private final LocalStackElement[] consumedLocals;

    private FrameDifferenceInfo(
            @Nullable LabelInfo label,
            @NotNull StackElement[] additionalStacks,
            @NotNull LocalStackElement[] additionalLocals,
            @NotNull StackElement[] consumedStack,
            @NotNull LocalStackElement[] consumedLocals
    )
    {
        this.label = label;
        this.additionalStack = additionalStacks;
        this.additionalLocals = additionalLocals;
        this.consumedStack = consumedStack;
        this.consumedLocals = consumedLocals;
    }

    @NotNull
    public static FrameDifferenceInfo same()
    {
        return SAME;
    }

    public static @NotNull Builder builder(@NotNull InstructionInfo instruction)
    {
        return new Builder(instruction);
    }

    public static class Builder
    {
        @NotNull
        private final InstructionInfo instruction;

        @Nullable
        private final LabelInfo labelInfo;

        @NotNull
        private final List<StackElement> additionalStack = new ArrayList<>();
        @NotNull
        private final List<LocalStackElement> additionalLocals = new ArrayList<>();
        @NotNull
        private final List<StackElement> consumedStack = new ArrayList<>();
        @NotNull
        private final List<LocalStackElement> consumedLocals = new ArrayList<>();

        public Builder(@NotNull InstructionInfo instruction)
        {
            this.instruction = instruction;
            this.labelInfo = instruction.assignedLabel();
        }

        @NotNull
        public Builder pushPrimitive(@NotNull StackElementType type)
        {
            if (!(type == StackElementType.INTEGER ||
                    type == StackElementType.FLOAT ||
                    type == StackElementType.LONG ||
                    type == StackElementType.DOUBLE))
                throw new IllegalArgumentException("Invalid primitive type: " + type);

            this.additionalStack.add(PrimitiveElement.of(this.instruction, type));
            if (type == StackElementType.LONG || type == StackElementType.DOUBLE)
            {
                // LONG と DOUBLE はスタックに 2 つの要素を追加する。
                this.additionalStack.add(new TopElement(this.instruction));
            }
            return this;
        }

        @NotNull
        public Builder pushReturnAddress()
        {
            this.additionalStack.add(new PrimitiveElement(this.instruction, StackElementType.RETURN_ADDRESS));
            return this;
        }

        @NotNull
        public Builder pushNull()
        {
            this.additionalStack.add(NullElement.of(this.instruction));
            return this;
        }

        @NotNull
        public Builder pushObjectRef(@NotNull TypeDescriptor reference)
        {
            this.additionalStack.add(new ObjectElement(this.instruction, reference));
            return this;
        }

        @NotNull
        public Builder pushUninitialized()
        {
            this.additionalStack.add(new UninitializedElement(this.instruction));
            return this;
        }

        @NotNull
        public Builder push(@NotNull StackElement element)
        {
            if (element instanceof NullElement)
                return this.pushNull();
            else if (element instanceof PrimitiveElement primitive)
                return this.pushPrimitive(primitive.type());
            else if (element instanceof ObjectElement object)
                return this.pushObjectRef(object.content());
            else if (element instanceof UninitializedElement)
                return this.pushUninitialized();
            else if (element instanceof StackElementCapsule capsule)
                return this.pushFromCapsule(capsule);
            else if (element instanceof TopElement)
                throw new IllegalArgumentException("Cannot push TopElement directly to stack");
            else
                throw new IllegalArgumentException("Unknown stack element type: " + element.getClass().getName());
        }

        @NotNull
        public Builder pop(@NotNull StackElement element)
        {
            if (element instanceof NullElement)
                return this.popNull();
            else if (element instanceof PrimitiveElement primitive)
                return this.popPrimitive(primitive.type());
            else if (element instanceof ObjectElement object)
                return this.popObjectRef(object.content());
            else if (element instanceof UninitializedElement)
                return this.popUninitialized();
            else if (element instanceof StackElementCapsule capsule)
                return this.popToCapsule(capsule);
            else if (element instanceof TopElement)
                throw new IllegalArgumentException("Cannot pop TopElement directly from stack");
            else
                throw new IllegalArgumentException("Unknown stack element type: " + element.getClass().getName());
        }

        @NotNull
        public Builder addLocalPrimitive(int idx, @NotNull StackElementType type)
        {
            if (!(type == StackElementType.INTEGER ||
                    type == StackElementType.FLOAT ||
                    type == StackElementType.LONG ||
                    type == StackElementType.DOUBLE ||
                    type == StackElementType.OBJECT))
                throw new IllegalArgumentException("Invalid local type: " + type);

            this.additionalLocals.add(new LocalStackElement(
                    this.instruction,
                    idx,
                    new PrimitiveElement(this.instruction, type)
            ));

            return this;
        }

        @NotNull
        public Builder addLocalObject(int idx, @NotNull TypeDescriptor reference)
        {
            this.additionalLocals.add(new LocalStackElement(
                    this.instruction,
                    idx,
                    new ObjectElement(this.instruction, reference)
            ));

            return this;
        }

        @NotNull
        public Builder addLocalFromCapsule(int idx, @NotNull StackElementCapsule capsule)
        {
            this.additionalLocals.add(new LocalStackElement(
                    this.instruction,
                    idx,
                    capsule
            ));

            return this;
        }

        @NotNull
        public Builder addLocalUninitialized(int idx)
        {
            this.additionalLocals.add(new LocalStackElement(
                    this.instruction,
                    idx,
                    new UninitializedElement(this.instruction)
            ));

            return this;
        }

        @NotNull
        public Builder addUninitializedThis()
        {
            this.additionalLocals.add(new LocalStackElement(
                    this.instruction,
                    0, // "this" は常にローカル変数のインデックス 0 にある
                    new UninitializedElement(this.instruction)
            ));

            return this;
        }

        @NotNull
        public Builder consumeUninitializedThis()
        {
            this.consumedLocals.add(new LocalStackElement(
                    this.instruction,
                    0, // "this" は常にローカル変数のインデックス 0 にある
                    new UninitializedElement(this.instruction)
            ));

            return this;
        }

        @NotNull
        public Builder popNull()
        {
            this.consumedStack.add(NullElement.of(this.instruction));
            return this;
        }

        @NotNull
        public Builder popPrimitive(@NotNull StackElementType type)
        {
            if (!(type == StackElementType.INTEGER ||
                    type == StackElementType.FLOAT ||
                    type == StackElementType.LONG ||
                    type == StackElementType.DOUBLE ||
                    type == StackElementType.OBJECT))
                throw new IllegalArgumentException("Invalid stack element type: " + type);

            if (type == StackElementType.LONG || type == StackElementType.DOUBLE)
            {
                // LONG と DOUBLE はスタックから 2 つの要素を消費する。
                this.consumedStack.add(new TopElement(this.instruction));
            }
            this.consumedStack.add(new PrimitiveElement(this.instruction, type));
            return this;
        }

        @NotNull
        public Builder popObjectRef(@NotNull TypeDescriptor reference)
        {
            this.consumedStack.add(new ObjectElement(this.instruction, reference));
            return this;
        }

        @NotNull
        public Builder pushObjectRef() // なんでもオブジェクト
        {
            this.additionalStack.add(new ObjectElement(this.instruction));
            return this;
        }

        @NotNull
        public Builder popObjectRef() // なんでもオブジェクト
        {
            this.consumedStack.add(new ObjectElement(this.instruction));
            return this;
        }

        @NotNull
        public Builder popToCapsule(@NotNull StackElementCapsule capsule)
        {
            // DUP や SWAP 用に， 現在の スタックの状態をカプセル化して保持する。
            this.consumedStack.add(capsule);
            return this;
        }

        @NotNull
        public Builder pushFromCapsule(@NotNull StackElementCapsule capsule)
        {
            // DUP や SWAP 用に， カプセル化されたスタックの状態を復元する。
            this.additionalStack.add(capsule);
            return this;
        }

        @NotNull
        public Builder popUninitialized()
        {
            this.consumedStack.add(new UninitializedElement(this.instruction));
            return this;
        }

        @NotNull
        public Builder consumeLocalPrimitive(int idx, @NotNull StackElementType type)
        {
            if (!(type == StackElementType.INTEGER ||
                    type == StackElementType.FLOAT ||
                    type == StackElementType.LONG ||
                    type == StackElementType.DOUBLE ||
                    type == StackElementType.OBJECT))
                throw new IllegalArgumentException("Invalid local type: " + type);

            this.consumedLocals.add(new LocalStackElement(
                    this.instruction,
                    idx,
                    new PrimitiveElement(this.instruction, type)
            ));

            return this;
        }

        @NotNull
        public Builder consumeLocalObject(int idx, @NotNull TypeDescriptor reference)
        {
            this.consumedLocals.add(new LocalStackElement(
                    this.instruction,
                    idx,
                    new ObjectElement(this.instruction, reference)
            ));

            return this;
        }

        @NotNull
        public Builder consumeLocalUninitialized(int idx)
        {
            this.consumedLocals.add(new LocalStackElement(
                    this.instruction,
                    idx,
                    new UninitializedElement(this.instruction)
            ));

            return this;
        }

        @NotNull
        public FrameDifferenceInfo build()
        {
            return new FrameDifferenceInfo(
                    this.labelInfo,
                    this.additionalStack.toArray(new StackElement[0]),
                    this.additionalLocals.toArray(new LocalStackElement[0]),
                    this.consumedStack.toArray(new StackElement[0]),
                    this.consumedLocals.toArray(new LocalStackElement[0])
            );
        }
    }
}
