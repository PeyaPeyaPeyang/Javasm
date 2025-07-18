package tokyo.peya.javasm.langjal.compiler.analyser;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LookupSwitchInsnNode;
import org.objectweb.asm.tree.TableSwitchInsnNode;
import tokyo.peya.javasm.langjal.compiler.FileEvaluatingReporter;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.LocalStackElement;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElement;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementCapsule;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackOperation;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.TopElement;
import tokyo.peya.javasm.langjal.compiler.exceptions.analyse.PropagationMismatchException;
import tokyo.peya.javasm.langjal.compiler.exceptions.analyse.StackUnderflowException;
import tokyo.peya.javasm.langjal.compiler.exceptions.analyse.UnknownJumpException;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;
import tokyo.peya.javasm.langjal.compiler.member.LabelInfo;
import tokyo.peya.javasm.langjal.compiler.member.LabelsHolder;

import java.util.ArrayList;
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
    private final Stack<StackElement> propagatedStack;
    @NotNull
    private final List<LocalStackElement> propagatedLocals;

    @NotNull
    private final Stack<StackElement> stack;
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
        this.propagatedStack = new Stack<>();
        this.propagatedLocals = new ArrayList<>();
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
                FramePropagation[] newPropagations = new FramePropagation[propagations.length + 1];
                System.arraycopy(propagations, 0, newPropagations, 0, propagations.length);
                newPropagations[propagations.length] = this.createPropagations(nextBlockLabel);
                propagations = newPropagations;
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
            this.propagatedStack.addAll(List.of(stack));
            this.propagatedLocals.addAll(List.of(locals));
            this.initialiseCurrentFrameInfo();  // 現在のフレーム情報を初期化
            return;
        }

        // 既に解析済みのスタックとローカル変数がある場合は，
        // Propagate で受け取ったスタックとローカル変数をマージする
        StackElement[] lastPropagatedStack = this.propagatedStack.toArray(new StackElement[0]);
        StackElement[] mergedStack = StackElementUtils.mergeStack(this.label, lastPropagatedStack, stack);
        this.propagatedStack.clear();
        Collections.addAll(this.propagatedStack, mergedStack);

        LocalStackElement[] lastPropagatedLocals = this.propagatedLocals.toArray(new LocalStackElement[0]);
        int minLocalSize = Math.min(lastPropagatedLocals.length, locals.length);
        LocalStackElement[] mergedLocals = StackElementUtils.mergeLocals(lastPropagatedLocals, locals, minLocalSize);
        this.propagatedLocals.clear();
        Collections.addAll(this.propagatedLocals, mergedLocals);

        this.initialiseCurrentFrameInfo();  // 現在のフレーム情報を初期化
    }

    private void initialiseCurrentFrameInfo()
    {
        this.locals.clear();
        this.stack.clear();

        this.locals.addAll(this.propagatedLocals);
        this.stack.addAll(this.propagatedStack);
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
                this.analysedInstructions.toArray(new AnalysedInstruction[0]),
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

            this.doesContainCriticalJump |= isCriticalJump(instruction);  // returnn も判定するので, ↓ if 外。
            // ジャンプターゲットを計算
            propagations.addAll(this.checkJump(instruction));

            this.analysedInstructions.add(new AnalysedInstruction(
                    instruction,
                    frameDifference,
                    this.stack.toArray(new StackElement[0]),  // この操作を実行した時点でのスナップショット
                    this.locals.toArray(new LocalStackElement[0])
            ));
        }

        this.context.postInfo(String.format(
                "Analysed instruction set '%s' with %d instructions, max stack size: %d, max local size: %d",
                this.label.name(), this.instructions.size(), this.maxStackSize, this.maxLocalSize
        ));

        return propagations.toArray(new FramePropagation[0]);
    }

    private List<FramePropagation> checkJump(@NotNull InstructionInfo info)
    {
        List<FramePropagation> propagations = new ArrayList<>();
        if (info.insn() instanceof JumpInsnNode jumpNode)
        {
            this.analyseJumpTarget(info, jumpNode);
            LabelInfo targetLabel = this.methodLabels.getLabelByNode(jumpNode.label);
            if (targetLabel == null)
                throw new UnknownJumpException(
                        "Unknown jump target specified by instruction: " + info,
                        info
                );

            propagations.add(this.createPropagations(targetLabel));
        }
        else if (info.insn() instanceof TableSwitchInsnNode tableSwitchNode)
        {
            // テーブルスイッチの場合は，すべてのターゲットラベルを登録
            for (LabelNode labelNode : tableSwitchNode.labels)
            {
                LabelInfo targetLabel = this.methodLabels.getLabelByNode(labelNode);
                if (targetLabel == null)
                    throw new UnknownJumpException(
                            "Unknown jump target specified by instruction: " + info,
                            info
                    );

                propagations.add(this.createPropagations(targetLabel));
            }
        }
        else if (info.insn() instanceof LookupSwitchInsnNode lookupSwitchNode)
        {
            // ルックアップスイッチの場合は，すべてのターゲットラベルを登録
            for (LabelNode label : lookupSwitchNode.labels)
            {
                LabelInfo targetLabel = this.methodLabels.getLabelByNode(label);
                if (targetLabel == null)
                    throw new UnknownJumpException(
                            "Unknown jump target specified by instruction: " + info,
                            info
                    );

                propagations.add(this.createPropagations(targetLabel));
            }
        }

        return propagations;
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

        // マージできるかチェック（マージ結果は使わないが，型チェックのために必要）
        StackElementUtils.mergeElement(poppedElement, expectedElement);

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
        StackElementUtils.checkSameType(existing, element.stackElement());
        return existing;
    }

    public List<InstructionInfo> getInstructions()
    {
        return Collections.unmodifiableList(this.instructions);
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
                || opcode == EOpcodes.ATHROW
                || opcode == EOpcodes.TABLESWITCH
                || opcode == EOpcodes.LOOKUPSWITCH;
    }
}
