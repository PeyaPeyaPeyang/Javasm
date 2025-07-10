package tokyo.peya.javasm.langjal.compiler;

import lombok.AccessLevel;
import lombok.Getter;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Label;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TryCatchBlockNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Getter
public class JALMethodEvaluator
{
    private final EvaluatingReporter context;
    private final ClassNode clazz;
    private final MethodNode method;

    @Getter(AccessLevel.NONE)
    private final List<LocalVariableInfo> locals;
    @Getter(AccessLevel.NONE)
    private final List<LabelInfo> labels;
    @Getter(AccessLevel.NONE)
    private final List<InstructionInfo> instructions;
    @Getter(AccessLevel.NONE)
    private final List<TryCatchDirective> tryCatchDirectives;

    private final Label globalStartLocalLabel;
    private final Label globalEndLocalLabel;

    private int bytecodeOffset;

    public JALMethodEvaluator(@NotNull EvaluatingReporter reporter, @NotNull ClassNode cn)
    {
        this.context = reporter;
        this.clazz = cn;
        this.method = new MethodNode();

        this.locals = new LinkedList<>();
        this.labels = new ArrayList<>();
        this.instructions = new ArrayList<>();
        this.tryCatchDirectives = new ArrayList<>();

        this.globalStartLocalLabel = new Label();
        this.globalEndLocalLabel = new Label();
    }

    public void evaluateMethod(@NotNull JALParser.MethodDefinitionContext method)
    {
        this.clazz.methods.add(this.method);

        this.evaluateMethodMetadata(method);
        this.evaluateMethodBody(method.methodBody());
        this.finaliseMethod();
    }

    private void finaliseMethod()
    {
        this.evaluateLocals();
        this.finaliseInstructionAndLabels();
        this.finaliseTryCatchDirectives();
    }

    private void finaliseInstructionAndLabels()
    {
        for (int i = 0; i < this.instructions.size(); i++)
        {
            // ラベルを登録
            for (LabelInfo label : this.labels)
                if (label.instructionIndex() == i)
                {
                    LabelNode labelNode = label.node();
                    this.method.instructions.add(labelNode);
                }

            InstructionInfo instruction = this.instructions.get(i);
            this.method.instructions.add(instruction.insn());
        }
    }

    private void evaluateLocals()
    {
        if (this.locals.isEmpty())
            return;  // ローカル変数がない場合は何もしない

        this.context.postInfo("Evaluating locals for method " + this.method.name + this.method.desc);
        this.prepareLocalEvaluation();

        for (LocalVariableInfo local : this.locals)
        {
            Label start = this.globalStartLocalLabel;
            Label end = this.globalEndLocalLabel;
            if (local.start() != null)
                start = local.start().label();
            if (local.end() != null)
                end = local.end().label();

            // ローカル変数をメソッドに登録
            this.method.visitLocalVariable(
                    local.name(),
                    local.type(),
                    null, // signature は未使用
                    start,
                    end,
                    local.index()
            );
        }

        this.finaliseLocalEvaluation();
    }

    private void finaliseTryCatchDirectives()
    {
        if (this.tryCatchDirectives.isEmpty())
            return;  // トライキャッチディレクティブがない場合は何もしない

        this.context.postInfo("Finalising try-catch directives for method " + this.method.name + this.method.desc);
        for (TryCatchDirective directive : this.tryCatchDirectives)
        {
            LabelNode tryBlock = directive.tryBlockStartLabel().node();
            LabelNode tryEndBlock = directive.tryBlockEndLabel().node();
            String exceptionType = directive.exceptionType();
            LabelNode catchBlock = directive.catchBlockLabel() == null ? null : directive.catchBlockLabel().node();
            LabelNode finallyBlock = directive.finallyBlockLabel() == null ? null : directive.finallyBlockLabel().node();

            // トライキャッチブロックをメソッドに追加
            this.method.tryCatchBlocks.add(new TryCatchBlockNode(
                    tryBlock,
                    tryEndBlock,
                    catchBlock,
                    exceptionType
            ));
            // finally ブロックがある場合は、トライキャッチブロックに追加
            if (finallyBlock != null)
            {
                // finally ブロックは try-catch ブロックの後に追加される
                this.method.tryCatchBlocks.add(new TryCatchBlockNode(
                        tryBlock,
                        tryEndBlock,
                        finallyBlock,
                        null  // finally ブロックは例外型を持たない
                ));
            }
        }
    }

    private void prepareLocalEvaluation()
    {
        if (checkAllLocalsHaveStart(this.locals))
        {
            LabelInfo startLabel = new LabelInfo("MBEGIN", this.globalStartLocalLabel, this.bytecodeOffset);
            this.labels.add(startLabel);
            if (this.method.instructions.size() > 0)
                this.method.instructions.insertBefore(this.method.instructions.get(0), startLabel.node());
            else
                this.method.instructions.add(startLabel.node());
            // ↑ START なので，いっちゃんさいしょ
        }
    }

    private void finaliseLocalEvaluation()
    {
        // 終了ラベルを持っていないローカル変数がある場合は、グローバルな終了ラベルを設定する。
        if (checkAllLocalsHaveEnd(this.locals))
        {
            LabelInfo endLabel = new LabelInfo("MEND", this.globalEndLocalLabel, this.bytecodeOffset);
            this.labels.add(endLabel);
            this.method.instructions.add(endLabel.node());
            // ↑ END なので，いっちゃんさいご
        }
    }

    private void evaluateMethodMetadata(@NotNull JALParser.MethodDefinitionContext method)
    {
        String desc = method.methodDescriptor().getText();
        String name = method.methodName().getText();
        int access = asAccess(method.accModMethod());

        this.method.name = name;
        this.method.desc = desc;
        this.method.access = access;
    }

    private void evaluateLabels(@NotNull JALParser.MethodBodyContext body)
    {
        int instructionCount = 0;
        for (JALParser.InstructionSetContext bodyItem : body.instructionSet())
        {
            if (bodyItem.label() != null)
            {
                // ラベルを登録
                String labelName = bodyItem.label().labelName().getText();
                this.registerLabel(labelName, instructionCount);
            }

            instructionCount += bodyItem.instruction().size();
        }
    }

    private void evaluateMethodBody(@NotNull JALParser.MethodBodyContext body)
    {
        this.context.postInfo("Evaluating method body for " + this.method.name + this.method.desc);

        this.method.visitCode();
        this.evaluateLabels(body);
        this.evaluateTryCatchDirectives(body);
        this.evaluateInstructions(body);
        this.method.visitEnd();
    }

    private void evaluateInstructions(@NotNull JALParser.MethodBodyContext body)
    {
        // 各命令を順に評価していく
        LabelInfo lastLabel = null;
        for (JALParser.InstructionSetContext bodyItem : body.instructionSet())
        {
            if (bodyItem.label() != null)
                lastLabel = this.resolveLabel(bodyItem.label().labelName().getText());

            for (JALParser.InstructionContext instruction: bodyItem.instruction())
            {
                // 命令を評価して，必要に応じてラベルを設定
                InstructionInfo info = JALInstructionEvaluator.evaluateInstruction(
                        this,
                        instruction,
                        lastLabel
                );
                if (info == null)
                    continue;

                this.addInstruction(info);
                lastLabel = null;
            }
        }

        if (this.instructions.isEmpty() || shouldAppendReturnOnLast(this.instructions.getLast()))
        {
            // 最後にRETURNがない場合は、デフォルトでRETURNを追加
            this.addInstruction(new InstructionInfo(
                    EOpcodes.RETURN,
                    this.bytecodeOffset,
                    lastLabel,
                    1
            ));
        }
    }

    private void evaluateTryCatchDirectives(JALParser.MethodBodyContext body)
    {
        for (JALParser.InstructionSetContext bodyItem : body.instructionSet())
        {
            if (bodyItem.tryCatchDirective() == null)
                continue;  // トライキャッチディレクティブがない場合はスキップ

            JALParser.LabelNameContext endLabel = bodyItem.tryCatchDirective().labelName();
            if (endLabel == null)
                throw new IllegalArgumentException("Try-catch directive must have an end label.");

            LabelInfo tryStartLabel = this.resolveLabel(bodyItem.label().labelName().getText());
            LabelInfo tryEndLabel = this.resolveLabel(endLabel.getText());

            JALParser.TryCatchDirectiveContext directiveContext = bodyItem.tryCatchDirective();
            for (JALParser.TryCatchDirectiveEntryContext entry: directiveContext.tryCatchDirectiveEntry())
                this.evaluateTryCatchDirective(
                        tryStartLabel,
                        tryEndLabel,
                        entry
                );
        }
    }

    private void evaluateTryCatchDirective(@NotNull LabelInfo tryBlockStartLabel,
                                           @NotNull LabelInfo tryBlockEndLabel,
                                           @NotNull JALParser.TryCatchDirectiveEntryContext entry)
    {
        JALParser.CatchDirectiveContext catchDirective = entry.catchDirective();
        JALParser.FinallyDirectiveContext finallyDirective = entry.finallyDirective();

        if (catchDirective == null && finallyDirective == null)
            throw new IllegalArgumentException("Try-catch directive must have at least one catch or finally block.");
        // finally は, catcHDirective 内に指定される場合がある
        if (finallyDirective == null)
            finallyDirective = catchDirective.finallyDirective();

        String exceptionType = null;
        if (catchDirective != null)
        {
            TerminalNode exceptionTypeNode = catchDirective.FULL_QUALIFIED_CLASS_NAME();
            if (exceptionTypeNode == null)
                throw new IllegalArgumentException("Catch directive must have an exception type.");
            exceptionType = exceptionTypeNode.getText();
        }

        // 各ラベルを解決
        JALParser.LabelNameContext catchLabel = catchDirective == null ? null : catchDirective.labelName();
        JALParser.LabelNameContext finallyLabel = finallyDirective == null ? null : finallyDirective.labelName();
        LabelInfo catchBlockLabel = null;
        LabelInfo finallyBlockLabel = null;
        if (catchLabel != null)
            catchBlockLabel = this.resolveLabel(catchLabel.getText());
        if (finallyLabel != null)
            finallyBlockLabel = this.resolveLabel(finallyLabel.getText());

        // トライキャッチディレクティブを登録
        TryCatchDirective directive = new TryCatchDirective(
                tryBlockStartLabel,
                tryBlockEndLabel,
                catchBlockLabel,
                exceptionType,
                finallyBlockLabel
        );
        this.tryCatchDirectives.add(directive);
    }

    private InstructionInfo addInstruction(@NotNull InstructionInfo instruction)
    {
        this.instructions.add(instruction);
        this.bytecodeOffset += instruction.instructionSize();
        return instruction;
    }

    @Nullable
    public LocalVariableInfo resolveLocalSafe(int localIndex)
    {
        for (LocalVariableInfo foundLocal : this.locals)  // リストのサイズと index は無関係。
            if (foundLocal.index() == localIndex)
                return foundLocal;
        return null;
    }

    @Nullable
    public LocalVariableInfo resolveLocalSafe(@NotNull String localName)
    {
        for (LocalVariableInfo localVar : this.locals)
            if (localName.equals(localVar.name()))
                return localVar;

        return null;
    }

    @NotNull
    public LocalVariableInfo resolveLocal(@NotNull JALParser.JvmInsArgLocalRefContext localRef,
                                          @NotNull String callerInsn)
    {
        TerminalNode localID = localRef.ID();
        TerminalNode localNumber = localRef.NUMBER();
        if (localID != null)
        {
            String localName = localID.getText();
            // ローカル変数名を参照
            LocalVariableInfo localVar = this.resolveLocalSafe(localName);
            if (localVar != null)
                return localVar;

            throw new IllegalArgumentException("Local variable with name '" + localName + "' is not defined.");
        }
        else if (localNumber != null)
        {
            int localIndex = EvaluatorCommons.asInteger(localNumber);
            if (localIndex < 0)
                throw new IllegalArgumentException("Local variable index cannot be negative: " + localIndex);

            // ローカル変数番号を参照
            LocalVariableInfo localVar = this.resolveLocalSafe(localIndex);
            if (localVar != null)
            {
                if (localIndex >= 3 && callerInsn.endsWith("load")) // xload 系のときに警告
                    this.warnLocalPerformance(localVar, callerInsn);
                return localVar;
            }

            throw new IllegalArgumentException("Local variable at index " + localIndex + " is not defined.");
        }

        throw new IllegalArgumentException("Invalid local reference: " + localRef.getText());
    }

    @Nullable
    public LocalVariableInfo resolveSafe(@NotNull JALParser.JvmInsArgLocalRefContext localRef)
    {
        TerminalNode localID = localRef.ID();
        TerminalNode localNumber = localRef.NUMBER();
        if (localID != null)
            return this.resolveLocalSafe(localID.getText());
        else if (localNumber != null)
        {
            int localIndex = EvaluatorCommons.asInteger(localNumber);
            if (localIndex < 0)
                return null;

            // ローカル変数番号を参照
            return this.resolveLocalSafe(localIndex);
        }

        return null;  // 無効な参照
    }

    @NotNull
    public LocalVariableInfo registerLocal(
            @NotNull JALParser.JvmInsArgLocalRefContext localRef,
            @NotNull String type
    )
    {
        // this.local.size() は index と無関係。
        TerminalNode localID = localRef.ID();
        TerminalNode localNumber = localRef.NUMBER();

        LocalVariableInfo registeredLocal = this.resolveSafe(localRef);
        if (registeredLocal != null)
        {
            if (localID == null)
                return registeredLocal;  // すでに登録されているので，そのまま返す。
            else // ID 指定なのにすでに登録されている場合は問題。
                throw new IllegalArgumentException(
                        "Local variable with name '" + localID.getText() + "' is already defined as " + registeredLocal.name()
                );
        }

        if (localID != null)
        {
            String localName = localID.getText();
            registeredLocal = new LocalVariableInfo(localName, type, this.locals.size());
        }
        else if (localNumber != null)
        {
            int localIndex = EvaluatorCommons.asInteger(localNumber);
            if (localIndex < 0)
                throw new IllegalArgumentException("Local variable index cannot be negative: " + localIndex);

            registeredLocal = new LocalVariableInfo(localIndex, type);
        }
        else
            throw new IllegalArgumentException("Invalid local reference: " + localRef.getText());

        this.locals.add(registeredLocal);
        // メソッドへの登録は後ほど。

        return registeredLocal;
    }

    @NotNull
    public LocalVariableInfo registerLocal(int idx, @NotNull String type)
    {
        if (idx < 0)
            throw new IllegalArgumentException("Local variable index cannot be negative: " + idx);

        // すでに登録されているか確認
        LocalVariableInfo existingLocal = this.resolveLocalSafe(idx);
        if (existingLocal != null)  // インデックス指定なのに，すでにあるのは問題。
            throw new IllegalArgumentException(
                    "Local variable at index " + idx + " is already defined as " + existingLocal.name()
            );

        // 新しいローカル変数を登録
        LocalVariableInfo newLocal = new LocalVariableInfo(idx, type);
        this.locals.add(newLocal);

        // メソッドへの登録は後ほど。
        return newLocal;
    }

    @NotNull
    public LabelInfo resolveLabel(@NotNull String labelName)
    {
        LabelInfo resolvedLabel = this.resolveLabelSafe(labelName);
        if (resolvedLabel == null)
            throw new IllegalArgumentException("Label '" + labelName + "' is not defined in this method.");

        return resolvedLabel;  // すでに登録されているラベルを返す
    }

    @Nullable
    public LabelInfo resolveLabelSafe(@NotNull String labelName)
    {
        for (LabelInfo existingLabel : this.labels)
        {
            if (existingLabel.name().equals(labelName))
                return existingLabel;  // すでに登録されているラベルを返す
        }

        return null;  // ラベルが見つからない場合は null を返す
    }

    @NotNull
    private LabelInfo registerLabel(@NotNull String labelName, int instructionIndex)
    {
        LabelInfo existingLabel = this.resolveLabelSafe(labelName);
        if (existingLabel != null)
            throw new IllegalArgumentException(
                    "Label '" + labelName + "' is already defined at instruction index "
                            + existingLabel.instructionIndex()
            );

        // 新しいラベルを登録
        Label newLabel = new Label();
        LabelInfo labelInfo = new LabelInfo(labelName, newLabel, instructionIndex);
        this.labels.add(labelInfo);

        // メソッドへの登録はあと
        return labelInfo;
    }

    @NotNull
    public LabelInfo registerLabel(@NotNull String labelName)
    {
        return this.registerLabel(labelName, this.instructions.size());
    }

    private void warnLocalPerformance(@NotNull LocalVariableInfo localVar, @NotNull String callerInsn)
    {
        // xLOAD_<n> が定義されているので，代わりにそっちを使ったほうが効率が良い(e.g. iload_1)
        this.context.postWarning(String.format(
                "Local variable %s is accessed in instruction '%s', " +
                        "but it is recommended to use %s_%d instead for better performance.",
                localVar.name(), callerInsn, localVar.name(), localVar.index()
        ));
    }

    private static boolean checkAllLocalsHaveStart(@NotNull List<LocalVariableInfo> locals)
    {
        return locals.stream()
                     .map(LocalVariableInfo::start)
                     .noneMatch(Objects::isNull);
    }

    private static boolean checkAllLocalsHaveEnd(@NotNull List<LocalVariableInfo> locals)
    {
        return locals.stream()
                     .map(LocalVariableInfo::end)
                     .noneMatch(Objects::isNull);
    }

    private static boolean shouldAppendReturnOnLast(InstructionInfo instruction)
    {
        return switch (instruction.opcode())
        {
            case EOpcodes.IRETURN, EOpcodes.LRETURN, EOpcodes.FRETURN,
                 EOpcodes.DRETURN, EOpcodes.ARETURN, EOpcodes.RETURN,
                 EOpcodes.ATHROW, EOpcodes.GOTO -> false; // これらの命令はRETURNを追加しない
            default -> true; // 他の命令が最後の場合はRETURNを追加する
        };
    }

    private static int asAccess(JALParser.AccModMethodContext methodNode)
    {
        int accessor = EvaluatorCommons.asAccessLevel(methodNode.accessLevel());
        for (JALParser.AccAttrMethodContext ctxt : methodNode.accAttrMethod())
        {
            if (ctxt.KWD_ACC_ATTR_STATIC() != null)
                accessor |= EOpcodes.ACC_STATIC;
            else if (ctxt.KWD_ACC_ATTR_FINAL() != null)
                accessor |= EOpcodes.ACC_FINAL;
            else if (ctxt.KWD_ACC_ATTR_SYNCHRONIZED() != null)
                accessor |= EOpcodes.ACC_SYNCHRONIZED;
            else if (ctxt.KWD_ACC_ATTR_BRIDGE() != null)
                accessor |= EOpcodes.ACC_BRIDGE;
            else if (ctxt.KWD_ACC_ATTR_VARARGS() != null)
                accessor |= EOpcodes.ACC_VARARGS;
            else if (ctxt.KWD_ACC_ATTR_NATIVE() != null)
                accessor |= EOpcodes.ACC_NATIVE;
            else if (ctxt.KWD_ACC_ATTR_ABSTRACT() != null)
                accessor |= EOpcodes.ACC_ABSTRACT;
            else if (ctxt.KWD_ACC_ATTR_STRICTFP() != null)
                accessor |= EOpcodes.ACC_STRICT;
            else if (ctxt.KWD_ACC_ATTR_SYNTHETIC() != null)
                accessor |= EOpcodes.ACC_SYNTHETIC;
        }

        return accessor;
    }
}
