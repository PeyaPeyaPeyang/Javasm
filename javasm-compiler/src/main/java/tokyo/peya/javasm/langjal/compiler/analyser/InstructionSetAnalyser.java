package tokyo.peya.javasm.langjal.compiler.analyser;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import tokyo.peya.javasm.langjal.compiler.FileEvaluatingReporter;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.LocalStackElement;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.NullElement;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.ObjectElement;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElement;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementCapsule;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackOperation;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.TopElement;
import tokyo.peya.javasm.langjal.compiler.exceptions.analyse.PropagationMismatchException;
import tokyo.peya.javasm.langjal.compiler.exceptions.analyse.StackElementMismatchedException;
import tokyo.peya.javasm.langjal.compiler.exceptions.analyse.StackUnderflowException;
import tokyo.peya.javasm.langjal.compiler.exceptions.analyse.UnknownJumpException;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.ClassReferenceType;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;
import tokyo.peya.javasm.langjal.compiler.member.LabelInfo;
import tokyo.peya.javasm.langjal.compiler.member.LabelsHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class InstructionSetAnalyser
{
    @NotNull
    private final FileEvaluatingReporter context;
    @NotNull
    private final LabelsHolder methodLabels;

    @Getter
    @NotNull
    private final LabelInfo label;
    @NotNull
    private final List<InstructionInfo> instructions;

    @NotNull
    private final List<AnalysedInstruction> analysedInstructions;

    @NotNull
    private final Stack<StackElement> stack;
    // !!! リストの index == ローカル変数のスロット番号 !!!  （インデックスではない）
    @NotNull
    private final List<LocalStackElement> locals;
    private final List<LabelInfo> jumpTargets;

    private boolean isOnceAnalysed;
    private boolean doesContainCriticalJump;
    private int maxStackSize;
    private int maxLocalSize;

    public InstructionSetAnalyser(@NotNull FileEvaluatingReporter context,
                                  @NotNull LabelsHolder methodLabels,
                                  @NotNull LabelInfo label,
                                  @NotNull List<InstructionInfo> instructions)
    {
        this.context = context;
        this.methodLabels = methodLabels;

        this.label = label;
        this.instructions = new ArrayList<>(instructions);

        this.analysedInstructions = new ArrayList<>();
        this.stack = new Stack<>();
        this.locals = new ArrayList<>();
        this.jumpTargets = new ArrayList<>();
    }

    @NotNull
    public InstructionSetAnalysisResult analyse(@NotNull FramePropagation propagation)
    {
        this.context.postInfo("Analysing instructions set named '%s' with %d instructions, propagation: %s".formatted(
                this.label.name(), this.instructions.size(), propagation
        ));

        this.applyPropagation(propagation);
        this.isOnceAnalysed = true;

        // スタックとローカル変数の動きをシミュレーションして，
        // 各命令のフレーム差分を計算する
        FramePropagation[] propagations = this.analyseInstructionFrames();
        if (!this.doesContainCriticalJump)
        {
            // 完全なジャンプ先がない場合は，現在のスタックとローカル変数をそのまま返す
            LabelInfo nextBlockLabel = this.methodLabels.getNextBlock(this.label);
            if (nextBlockLabel != null)
            {
                // 次のブロックがある場合は，そのブロックに対する伝搬情報を末尾に作る
                propagations = new FramePropagation[propagations.length + 1];
                System.arraycopy(propagations, 0, propagations, 0, propagations.length - 1);
                propagations[propagations.length - 1] = this.createPropagations(nextBlockLabel);
            }
        }

        return new InstructionSetAnalysisResult(
                this.analysedInstructions.toArray(new AnalysedInstruction[0]),
                propagations,
                this.stack.toArray(new StackElement[0]),
                this.locals.toArray(new LocalStackElement[0]),
                this.maxStackSize,
                this.maxLocalSize
        );
    }

    private void applyPropagation(@NotNull FramePropagation propagation)
    {
        if (!propagation.receiver().equals(this.label))
            throw new PropagationMismatchException(propagation, this.label);

        StackElement[] stack = propagation.stack();
        LocalStackElement[] locals = propagation.locals();

        if (!this.isOnceAnalysed)
        {
            // 初回の解析時は，メソッド本体から貰った Propagation のスタックとローカルをそのまま適用する
            // この時点ではまだスタックやローカル変数は空なので，そのまま追加する
            this.stack.addAll(List.of(stack));
            this.locals.addAll(List.of(locals));
            return;
        }

        // 既に解析済みのスタックとローカル変数がある場合は，
        // Propagate で受け取ったスタックとローカル変数をマージする

        // 現在到達地点で，他経路からのスタックとローカル変数の数が一致しないのはエラー（JVM 仕様）
        if (stack.length != this.stack.size())
            throw new PropagationMismatchException(
                    propagation, this.label,
                    String.format(getSizeMismatchMessage(
                            this.label,
                            "stack",
                            this.stack.size(),
                            stack.length, this.stack,
                            List.of(stack)
                    ))
            );
        else if (locals.length != this.locals.size())
            throw new PropagationMismatchException(
                    propagation, this.label,
                    String.format(getSizeMismatchMessage(
                            this.label, "locals",
                            this.locals.size(),
                            locals.length,
                            this.locals,
                            List.of(locals)
                    ))
            );

        // 既存のスタックと新しいスタックの要素をマージする
        for (int i = 0; i < stack.length; i++)
        {
            StackElement existingElement = this.stack.get(i);
            StackElement newElement = stack[i];
            // 既存のスタック要素と新しいスタック要素の型が一致しない場合は例外を投げる
            if (existingElement.type() != newElement.type())
                throw new PropagationMismatchException(
                        propagation, this.label,
                        String.format(
                                "Expected stack element type %s at index %d, but got %s\nCurrent stack: %s\nPropagation stack: %s",
                                existingElement.type(),
                                i,
                                newElement.type(),
                                stackToString(this.stack),
                                stackToString(stack)
                        )
                );

            // スタックをマージする
            this.stack.set(i, mergeElement(existingElement, newElement));
        }

        // 既存のローカル変数と新しいローカル変数の要素をマージする
        for (int i = 0; i < locals.length; i++)
        {
            LocalStackElement existingElement = this.locals.get(i);
            LocalStackElement newElement = locals[i];
            // 既存のローカル変数要素と新しいローカル変数要素の型が一致しない場合は例外を投げる
            if (existingElement.stackElement().type() != newElement.stackElement().type())
                throw new PropagationMismatchException(
                        propagation, this.label,
                        String.format(
                                "Expected local element type %s at index %d, but got %s\nCurrent locals: %s\nPropagation locals: %s",
                                existingElement.stackElement().type(), i, newElement.stackElement().type(),
                                stackToString(this.locals), stackToString(locals)
                        )
                );

            // ローカル変数をマージする
            this.locals.set(
                    i, new LocalStackElement(
                            newElement.producer(),
                            existingElement.index(),
                            mergeElement(existingElement.stackElement(), newElement.stackElement())
                    )
            );
        }
    }

    private void analyseJumpTarget(@NotNull InstructionInfo instructionInfo, @NotNull JumpInsnNode jumpNode)
    {
        LabelNode targetNode = jumpNode.label;
        if (targetNode == null)
            throw new UnknownJumpException("Jump instruction has no target label: " + instructionInfo, instructionInfo);

        LabelInfo targetLabel = this.methodLabels.getLabelByNode(targetNode);
        if (targetLabel == null)
        {
            // ターゲットラベルが見つからない場合は，エラーを投げる
            throw new UnknownJumpException(
                    "Unknown jump target specified by instruction: " + instructionInfo,
                    instructionInfo
            );
        }

        // ターゲットラベルを登録
        if (!this.jumpTargets.contains(targetLabel))
            this.jumpTargets.add(targetLabel);
    }

    private FramePropagation createPropagations(@NotNull LabelInfo toLabel)
    {
        // 各ジャンプ先のために，伝搬情報を作る
        StackElement[] stackCopy = this.stack.toArray(new StackElement[0]);
        LocalStackElement[] localsCopy = this.locals.toArray(new LocalStackElement[0]);
        return new FramePropagation(
                this.label,
                toLabel,
                stackCopy,
                localsCopy,
                this.maxStackSize,
                this.maxLocalSize
        );
    }

    private FramePropagation[] analyseInstructionFrames()
    {
        List<FramePropagation> propagations = new ArrayList<>();
        for (InstructionInfo instruction : this.instructions)
        {
            AbstractInstructionEvaluator<?> instructionProducer = instruction.evaluator();
            FrameDifferenceInfo frameDifference = instructionProducer.getFrameDifferenceInfo(instruction);

            StackOperation[] stackOperations = frameDifference.getStackOperations();
            this.processStackLocalDifference(instruction, stackOperations);

            // ジャンプターゲットを計算
            if (instruction.insn() instanceof JumpInsnNode jumpNode)
            {
                this.analyseJumpTarget(instruction, jumpNode);
                this.doesContainCriticalJump |= isCriticalJump(instruction);
                LabelInfo targetLabel = this.methodLabels.getLabelByNode(jumpNode.label);
                if (targetLabel == null)
                    throw new UnknownJumpException(
                            "Unknown jump target specified by instruction: " + instruction,
                            instruction
                    );

                propagations.add(this.createPropagations(targetLabel));
            }

            this.analysedInstructions.add(new AnalysedInstruction(instruction, frameDifference));
        }

        this.context.postInfo(String.format(
                "Analysed instruction set '%s' with %d instructions, max stack size: %d, max local size: %d",
                this.label.name(), this.instructions.size(), this.maxStackSize, this.maxLocalSize
        ));

        return propagations.toArray(new FramePropagation[0]);
    }

    private void updateMaxes()
    {
        this.maxStackSize = Math.max(this.maxStackSize, this.stack.size());
        this.maxLocalSize = Math.max(this.maxLocalSize, this.locals.size());
    }

    private void processStackLocalDifference(@NotNull InstructionInfo instruction,
                                             @NotNull StackOperation[] stackLocalOperations)
    {
        for (StackOperation stackOperation : stackLocalOperations)
        {
            StackOperation.StackOperationType type = stackOperation.type();
            StackElement element = stackOperation.element();
            // 変数のようなものなので，参照/保持する必要がある
            switch (type)
            {
                case PUSH:
                    if (element instanceof StackElementCapsule capsule)
                        this.pushStackElement(capsule.getElement());  // 一次退避した値を入れる
                    else if (element instanceof LocalStackElement localElement)
                        this.addLocalElement(localElement);  // ローカル変数を追加
                    else
                        this.pushStackElement(element);
                    break;
                case POP:
                    if (element instanceof LocalStackElement localElement)
                        this.consumeLocalElement(localElement);
                    else
                        this.popStackElement(instruction, element);
                    break;
            }

            this.updateMaxes();
        }
    }

    private void pushStackElement(@NotNull StackElement element)
    {
        this.stack.push(element);
    }

    private StackElement popStackElement(@NotNull InstructionInfo instruction, @NotNull StackElement expectedElement)
    {
        if (this.stack.isEmpty())
            throw new StackUnderflowException(instruction, expectedElement);

        StackElement poppedElement = this.stack.pop();
        if (expectedElement instanceof StackElementCapsule capsule)
        {
            capsule.setElement(poppedElement);  // Capsule の場合は，その中の要素を使う
            return expectedElement;  // Capsule の場合は，その中の要素を使うだけなので，後のチェックは省く。
        }

        // 互換性チェック
        this.checkType(instruction, poppedElement, expectedElement);
        assert poppedElement.type() == expectedElement.type(); // ↑によって保証

        // マージできるかチェック（マージ結果は使わないが，型チェックのために必要）
        this.mergeElement(poppedElement, expectedElement);

        return poppedElement;  // マージ結果は使わないが，型チェックのために必要
    }

    private void addLocalElement(@NotNull LocalStackElement localElement)
    {
        int index = localElement.index();

        // Capsuleの処理
        StackElement actualElement = localElement.stackElement();
        if (actualElement instanceof StackElementCapsule capsule)
            localElement = new LocalStackElement(
                    capsule.producer(), index, capsule.getElement()  // Capsule の中の要素を使う
            );

        StackElementType type = localElement.type();

        TopElement top = new TopElement(localElement.producer());

        // 使用するスロット数を決定
        int requiredSlots = (type == StackElementType.LONG || type == StackElementType.DOUBLE) ? 2: 1;

        // スロット数が足りない場合は，必要なスロット数だけローカル変数を追加
        while (this.locals.size() < index + requiredSlots)  // リストのインデックス = ローカル変数のスロット番号
            this.locals.add(new LocalStackElement(localElement.producer(), this.locals.size(), top));

        // マージはしない：合流してきたものではないから
        this.locals.set(index, localElement);
        if (requiredSlots == 2)  // 長さ2の要素を追加する場合は，次のスロットを TOP で埋める（JVM 仕様）
        {
            int topIndex = index + 1;
            this.locals.set(topIndex, new LocalStackElement(localElement.producer(), topIndex, top));
        }
    }

    private StackElement consumeLocalElement(@NotNull LocalStackElement element)
    {
        int index = element.index();
        if (index < 0 || index >= this.locals.size())
            throw new IllegalArgumentException("Local variable index out of bounds: " + index);

        StackElement existing = this.locals.get(index).stackElement();
        if (element.stackElement() instanceof StackElementCapsule capsule)
        {
            capsule.setElement(existing);  // Capsule の場合は，その中の要素を使う
            return existing;  // Capsule の場合は，その中の要素を使うだけなので，後のチェックは省く。
        }

        // 互換性チェック
        this.checkType(element.producer(), existing, element.stackElement());
        return existing;
    }

    private void checkType(@NotNull InstructionInfo actualElementProducer,
                           @NotNull StackElement element, @NotNull StackElement expectedElement)
    {
        if (element.type() != expectedElement.type())
            throw new StackElementMismatchedException(
                    actualElementProducer, expectedElement, element,
                    "Cannot merge stack element with different types: " +
                            element.type() + " produced by " + element.producer() +
                            " and " + expectedElement.type() + " produced by " + expectedElement.producer()
            );
    }

    private StackElement mergeElement(@NotNull StackElement existingElement, @NotNull StackElement newElement)
    {
        // null は任意のオブジェクト型と互換性があるのでそのまま返す
        if (newElement instanceof NullElement)
            return newElement;
        else if (existingElement instanceof NullElement)
            return existingElement;

        this.checkType(newElement.producer(), newElement, existingElement);
        return switch (existingElement.type())
        {
            case TOP, INTEGER, FLOAT, LONG, DOUBLE, NULL, RETURN_ADDRESS, NOP -> existingElement;
            // オブジェクト型はマージする
            case OBJECT ->
            {
                assert newElement instanceof ObjectElement;  // #checkType() でチェック済み
                yield mergeObjects((ObjectElement) existingElement, (ObjectElement) newElement);
            }
            case UNINITIALIZED_THIS, UNINITIALIZED -> newElement;  // Uninitialized は新しい要素をそのまま返す
        };
    }

    private ObjectElement mergeObjects(@NotNull ObjectElement existingObject, @NotNull ObjectElement newObject)
    {
        TypeDescriptor existingType = existingObject.content();
        TypeDescriptor newType = newObject.content();
        if (existingType.equals(newType))
        {
            // 型が同じならそのまま返す
            return newObject;
        }

        if (existingType.getArrayDimensions() != newType.getArrayDimensions())
        {
            // 配列の次元が異なる場合はエラー
            throw new StackElementMismatchedException(
                    newObject.producer(), existingObject, newObject,
                    "Cannot merge object stack elements with different array dimensions: " +
                            existingType + "produced by " + existingObject.producer()
                            + " and " + newType + " produced by " + newObject.producer()
            );
        }

        if (existingType.getBaseType().equals(newType.getBaseType()))
            return newObject; // 基本型が同じならそのまま返す
        else if (existingType.getBaseType().isPrimitive() || newType.getBaseType().isPrimitive())
        {
            // ここに到達するプリミティブは，型が違うことが保証されている
            throw new StackElementMismatchedException(
                    newObject.producer(), existingObject, newObject,
                    "Cannot merge object stack elements with different primitive types: " +
                            existingType + " produced by " + existingObject.producer()
                            + " and " + newType + " produced by " + newObject.producer()
            );

        }

        // 共通のスーパークラスを求める
        int arayDimension = existingType.getArrayDimensions();  // 配列の次元は同じなのでどちらか一方を使う
        ClassReferenceType commonSuperType = getCommonSuperType(
                (ClassReferenceType) existingType.getBaseType(),  // 参照型なのは保証されている
                (ClassReferenceType) newType.getBaseType()
        );

        TypeDescriptor mergedType = new TypeDescriptor(commonSuperType, arayDimension);
        // 新しい ObjectElement を返す
        return new ObjectElement(newObject.producer(), mergedType);
    }

    private ClassReferenceType getCommonSuperType(@NotNull ClassReferenceType type1, @NotNull ClassReferenceType type2)
    {
        ClassLoader classLoader = this.getClass().getClassLoader();
        Class<?> class1, class2;
        try
        {
            class1 = Class.forName(type1.getDottedName(), false, classLoader);
            class2 = Class.forName(type2.getDottedName(), false, classLoader);
        }
        catch (ClassNotFoundException e)
        {
            this.context.postWarning("Unknown class in stack element merge: %s or %s".formatted(
                    type1.getDottedName(), type2.getDottedName()
            ));
            return type1;  // クラスが見つからない場合は，片方の型をそのまま返す
        }

        if (class1.isAssignableFrom(class2))
            return type1;  // type1 が type2 のスーパークラスなら type1 を返す
        else if (class2.isAssignableFrom(class1))
            return type2;  // type2 が type1 のスーパークラスなら type2 を返す

        if (class1.isInterface() || class2.isInterface())
            return ClassReferenceType.parse(Object.class.getName());  // インターフェースの場合は Object を返す

        do
        {
            class1 = class1.getSuperclass();
        }
        while (class1.isAssignableFrom(class2));

        // 共通のスーパークラスを返す。 Object まで到達するので，その時は Object を返す
        return ClassReferenceType.parse(class1.getName());
    }

    public List<InstructionInfo> getInstructions()
    {
        return Collections.unmodifiableList(this.instructions);
    }

    private static String getSizeMismatchMessage(@NotNull LabelInfo label,
                                                 @NotNull String stackType,
                                                 int expectedSize, int actualSize,
                                                 @NotNull Collection<? extends StackElement> expectedStack,
                                                 @NotNull Collection<? extends StackElement> stack)
    {
        return String.format(
                """
                        Expected %s size %d at label '%s', but got %d.
                         This error indicates that in instruction set '%s', which is reached from several instruction jump routes,
                        the size of the %s stack for each route is different and the stacks are not consistent.
                         JVM must not differ in the number of stacks and their element types
                        at the destination of the jump.
                        
                        Current stack: %s
                        Applying stack: %s""",
                stackType, expectedSize, label.name(), actualSize,
                label.name(), stackType, stackToString(stack), stackToString(expectedStack)
        );
    }

    private static boolean isCriticalJump(@NotNull InstructionInfo instruction)
    {
        int opcode = instruction.insn().getOpcode();
        return opcode == EOpcodes.GOTO
                || opcode == EOpcodes.GOTO_W
                || opcode == EOpcodes.RETURN
                || opcode == EOpcodes.ARETURN
                || opcode == EOpcodes.IRETURN
                || opcode == EOpcodes.FRETURN
                || opcode == EOpcodes.LRETURN
                || opcode == EOpcodes.DRETURN
                || opcode == EOpcodes.ATHROW;
    }

    private static String stackToString(@NotNull Collection<? extends StackElement> stack)
    {
        return stackToString(stack.toArray(new StackElement[0]));
    }

    private static <T extends StackElement> String stackToString(@NotNull T[] stack)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < stack.length; i++)
        {
            sb.append(stackElementToString(stack[i]));
            if (i < stack.length - 1)
                sb.append(" | ");
        }

        sb.append("]");
        return sb.toString();
    }

    private static String stackElementToString(@NotNull StackElement element)
    {
        return switch (element.type())
        {
            case TOP -> "T";
            case UNINITIALIZED_THIS -> "U_T";
            case INTEGER -> "I";
            case FLOAT -> "F";
            case LONG -> "L";
            case DOUBLE -> "D";
            case OBJECT -> ((ObjectElement) element).content().toString();
            case NULL -> " ";
            case RETURN_ADDRESS -> "RA";
            case UNINITIALIZED -> "U";
            case NOP -> "NOP";
        };
    }
}
