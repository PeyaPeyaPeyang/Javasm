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
import tokyo.peya.javasm.langjal.compiler.jvm.MethodDescriptor;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Getter
public class JALMethodEvaluator
{
    private final FileEvaluatingReporter context;
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

    private final LabelInfo globalStartLocalLabel;
    private final LabelInfo globalEndLocalLabel;

    private LabelInfo currentLabel; // 現在解析中の最後のラベル
    private int bytecodeOffset;

    public JALMethodEvaluator(@NotNull FileEvaluatingReporter reporter, @NotNull ClassNode cn)
    {
        this.context = reporter;
        this.clazz = cn;
        this.method = new MethodNode();

        this.locals = new LinkedList<>();
        this.labels = new ArrayList<>();
        this.instructions = new ArrayList<>();
        this.tryCatchDirectives = new ArrayList<>();

        this.globalStartLocalLabel = new LabelInfo("MBEGIN", new Label(), this.bytecodeOffset);
        this.globalEndLocalLabel = new LabelInfo("MEND", new Label(), -1);
    }

    public void evaluateMethod(@NotNull JALParser.MethodDefinitionContext method)
    {
        this.clazz.methods.add(this.method);

        this.evaluateMethodMetadata(method);
        this.evaluateMethodParameters(method);
        this.evaluateMethodBody(method.methodBody());
        this.finaliseMethod();
    }

    private void evaluateMethodParameters(@NotNull JALParser.MethodDefinitionContext method)
    {
        JALParser.MethodDescriptorContext desc = method.methodDescriptor();
        MethodDescriptor descriptor = MethodDescriptor.parse(desc.getText());
        TypeDescriptor[] parameters = descriptor.getParameterTypes();
        int accessor = asAccess(method.accModMethod());

        int currentIndex = 0;
        boolean isInstanceMethod = (accessor & EOpcodes.ACC_STATIC) == 0;
        if (isInstanceMethod)
        {
            // インスタンスメソッドの場合、this パラメータを追加
            String thisParamName = "this";
            TypeDescriptor thisParamType = descriptor.getReturnType(); // インスタンスメソッドの戻り値が this の型
            // パラメータをローカル変数として登録
            this.registerParameter(thisParamName, thisParamType, currentIndex++);
        }

        for (int i = 0; i < parameters.length; i++)
        {
            TypeDescriptor paramType = parameters[i];
            String paramName = String.format("arg%05d", i);
            // パラメータをローカル変数として登録
            if (paramType.getBaseType().getCategory() == 2)
            {
                this.registerParameter(paramName, paramType, currentIndex++);
                currentIndex++; // カテゴリ２は ２スロット使うため，インデックスを進める
            }
            else
                this.registerParameter(paramName, paramType, currentIndex++);
        }
    }

    private void registerParameter(@NotNull String paramName,
                                   @NotNull TypeDescriptor type,
                                   int index)
    {
        // パラメータをローカル変数として登録
        LocalVariableInfo localVar = new LocalVariableInfo(
                paramName,
                type,
                null,
                null,
                index,
                true
        );
        this.locals.add(localVar);
    }

    private void finaliseMethod()
    {
        this.evaluateLocals();
        this.finaliseInstructionAndLabels();
        this.finaliseTryCatchDirectives();
    }

    private void finaliseInstructionAndLabels()
    {
        for (InstructionInfo instruction : this.instructions)
        {
            if (instruction.assignedLabel() != null)  // 命令にラベルが割り当てられている場合
                this.method.instructions.add(instruction.assignedLabel().node());

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
            if (local.isParameter())
                continue;  // パラメータはローカル変数として登録しない

            Label start = this.globalStartLocalLabel.label();
            Label end = this.globalEndLocalLabel.label();
            if (local.start() != null)
                start = local.start().label();
            if (local.end() != null)
                end = local.end().label();

            String typeDescriptor = local.type().toString();
            // ローカル変数をメソッドに登録
            this.method.visitLocalVariable(
                    local.name(),
                    typeDescriptor,
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
            LabelNode catchBlock = directive.catchBlockLabel() == null ? null: directive.catchBlockLabel().node();
            LabelNode finallyBlock = directive.finallyBlockLabel() == null ? null: directive.finallyBlockLabel().node();

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
        LabelNode globalStartNode = this.globalStartLocalLabel.node();
        if (this.method.instructions.size() > 0)
            this.method.instructions.insertBefore(this.method.instructions.get(0), globalStartNode);
        else
            this.method.instructions.add(globalStartNode);
    }

    private void finaliseLocalEvaluation()
    {
        // 終了ラベルを持っていないローカル変数がある場合は、グローバルな終了ラベルを設定する。
        LabelNode globalEndNode = this.globalEndLocalLabel.node();
        this.method.instructions.add(globalEndNode);
        // ↑ END なので，いっちゃんさいご
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
        LabelInfo assignLabel = null;  // 命令に割り当てるラベル。１命令のみが割り当てられる。
        for (JALParser.InstructionSetContext bodyItem : body.instructionSet())
        {
            if (bodyItem.label() != null)
                assignLabel = this.currentLabel = this.resolveLabel(bodyItem.label().labelName().getText());

            for (JALParser.InstructionContext instruction : bodyItem.instruction())
            {
                // 命令を評価して，必要に応じてラベルを設定
                InstructionInfo info = JALInstructionEvaluator.evaluateInstruction(
                        this,
                        instruction,
                        assignLabel
                );
                if (info == null)
                    continue;

                this.addInstruction(info);
                assignLabel = null;
            }
        }

        if (this.instructions.isEmpty() || shouldAppendReturnOnLast(this.instructions.getLast()))
        {
            // 最後にRETURNがない場合は、デフォルトでRETURNを追加
            this.addInstruction(new InstructionInfo(
                    EOpcodes.RETURN,
                    this.bytecodeOffset,
                    assignLabel,
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
            for (JALParser.TryCatchDirectiveEntryContext entry : directiveContext.tryCatchDirectiveEntry())
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
        JALParser.LabelNameContext catchLabel = catchDirective == null ? null: catchDirective.labelName();
        JALParser.LabelNameContext finallyLabel = finallyDirective == null ? null: finallyDirective.labelName();
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

    private int getNextLocalIndex()
    {
        if (this.locals.isEmpty())
            return 0;  // 最初のローカル変数はインデックス 0 から始まる

        LocalVariableInfo maxLocalNum = this.locals.stream()
                                                   .max(Comparator.comparingInt(LocalVariableInfo::index))
                                                   .orElseThrow(() -> new IllegalStateException(
                                                           "No local variables registered."));

        TypeDescriptor lastType = maxLocalNum.type();
        if (lastType.getBaseType().getCategory() == 2)
        {
            // カテゴリ２の型は２スロット使用するので、次のインデックスは +2
            return maxLocalNum.index() + 2;
        }
        else
        {
            // カテゴリ１の型は１スロット使用するので、次のインデックスは +1
            return maxLocalNum.index() + 1;
        }
    }

    @Nullable
    private LocalVariableInfo checkAlreadyRegistered(
            @NotNull JALParser.JvmInsArgLocalRefContext localRef,
            @Nullable TerminalNode localID
    )
    {
        LocalVariableInfo registeredLocal = this.resolveSafe(localRef);
        if (registeredLocal == null)
            return null;  // まだ登録されていない場合は null を返す

        if (localID == null)
            return registeredLocal;
        else  // 同じ ID で登録されている場合は例外を投げる
            throw new IllegalArgumentException(
                    "Local variable with name '" + localID.getText() + "' is already defined as " + registeredLocal.name()
            );
    }

    @NotNull
    public LocalVariableInfo registerLocal(
            @NotNull JALParser.JvmInsArgLocalRefContext localRef,
            @NotNull TypeDescriptor type,
            @Nullable String name,
            @Nullable LabelInfo endLabel
    )
    {
        return this.registerLocal(localRef, type, name, this.currentLabel, endLabel);
    }

    @NotNull
    public LocalVariableInfo registerLocal(int idx,
                                           @NotNull TypeDescriptor type,
                                           @Nullable String name,
                                           @Nullable LabelInfo endLabel)
    {
        return this.registerLocal(idx, type, name, this.currentLabel, endLabel);
    }

    @NotNull
    public LocalVariableInfo registerLocal(
            @NotNull JALParser.JvmInsArgLocalRefContext localRef,
            @NotNull TypeDescriptor type,
            @Nullable String name,
            @Nullable LabelInfo startLabel,
            @Nullable LabelInfo endLabel
    )
    {
        // this.local.size() は index と無関係。
        TerminalNode localID = localRef.ID();
        TerminalNode localNumber = localRef.NUMBER();

        LocalVariableInfo registeredLocal = checkAlreadyRegistered(localRef, localID);
        if (registeredLocal != null)
            return registeredLocal;  // すでに登録されている場合はそれを返す

        String newLocalName;
        int newLocalIndex;
        if (localID != null)
        {
            newLocalName = localID.getText();
            newLocalIndex = this.getNextLocalIndex();
        }
        else if (localNumber != null)
        {
            newLocalIndex = EvaluatorCommons.asInteger(localNumber);
            if (newLocalIndex < 0)
                throw new IllegalArgumentException("Local variable index cannot be negative: " + newLocalIndex);

            newLocalName = name == null ? String.format("local%05d", newLocalIndex): name;

        }
        else
            throw new IllegalArgumentException("Invalid local reference: " + localRef.getText());

        // 新しいローカル変数を登録
        registeredLocal = new LocalVariableInfo(
                newLocalName,
                type,
                startLabel,
                endLabel,
                newLocalIndex
        );
        this.locals.add(registeredLocal);
        // メソッドへの登録は後ほど。

        return registeredLocal;
    }

    @NotNull
    public LocalVariableInfo registerLocal(int idx,
                                           @NotNull TypeDescriptor type,
                                           @Nullable String name,
                                           @Nullable LabelInfo startLabel,
                                           @Nullable LabelInfo endLabel)
    {
        if (idx < 0)
            throw new IllegalArgumentException("Local variable index cannot be negative: " + idx);

        // すでに登録されているか確認
        LocalVariableInfo existingLocal = this.resolveLocalSafe(idx);
        if (existingLocal != null)  // インデックス指定なのに，すでにあるのは問題。
            throw new IllegalArgumentException(
                    "Local variable at index " + idx + " is already defined as " + existingLocal.name()
            );

        String nameToUse = name == null ? String.format("local%05d", idx): name;
        // 新しいローカル変数を登録
        LocalVariableInfo newLocal = new LocalVariableInfo(
                nameToUse,
                type,
                startLabel,
                endLabel,
                idx
        );
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
