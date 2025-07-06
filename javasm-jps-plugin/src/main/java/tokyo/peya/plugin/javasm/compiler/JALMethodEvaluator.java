package tokyo.peya.plugin.javasm.compiler;

import lombok.Getter;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Label;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
public class JALMethodEvaluator
{
    private final EvaluatingReporter context;
    private final ClassNode clazz;
    private final MethodNode method;

    private final List<LocalVariableInfo> locals;
    private final List<LabelInfo> labels;
    private final List<InstructionInfo> instructions;

    private int bytecodeOffset;

    public JALMethodEvaluator(@NotNull EvaluatingReporter reporter, @NotNull ClassNode cn)
    {
        this.context = reporter;
        this.clazz = cn;
        this.method = new MethodNode();

        this.locals = new LinkedList<>();
        this.labels = new ArrayList<>();
        this.instructions = new ArrayList<>();
    }

    public void evaluateMethod(@NotNull JALParser.MethodDefinitionContext method)
    {
        this.clazz.methods.add(this.method);

        this.evaluateMethodMetadata(method);
        this.evaluateMethodBody(method.methodBody());
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


    private void evaluateMethodBody(@NotNull JALParser.MethodBodyContext body)
    {
        this.method.visitCode();

        // 各命令を順に評価していく
        LabelInfo lastLabel = null;
        for (JALParser.MethodBodyItemContext bodyItem: body.methodBodyItem())
        {
            if (bodyItem.label() != null)
                lastLabel = this.evaluateLabel(bodyItem.label());
            else if (bodyItem.instruction() != null)
            {
                // 命令を評価して，必要に応じてラベルを設定
                InstructionInfo info = JALInstructionEvaluator.evaluateInstruction(
                        this,
                        bodyItem.instruction(),
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
        
        this.method.visitEnd();
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

    private LabelInfo evaluateLabel(@NotNull JALParser.LabelContext label)
    {
        String labelName = label.labelName().getText();
        Label labelObj = new Label();
        this.method.visitLabel(labelObj);

        LabelInfo labelInfo = new LabelInfo(labelName, labelObj, this.bytecodeOffset);
        this.labels.add(labelInfo);

        return labelInfo;
    }

    private InstructionInfo addInstruction(@NotNull InstructionInfo instruction)
    {
        this.instructions.add(instruction);
        this.bytecodeOffset += instruction.instructionSize();
        this.method.instructions.add(instruction.insn());

        return instruction;
    }

    private static int asAccess(JALParser.AccModMethodContext methodNode)
    {
        int accessor = EvaluatorCommons.asAccessLevel(methodNode.accessLevel());
        for (JALParser.AccAttrMethodContext ctxt: methodNode.accAttrMethod())
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
    public LocalVariableInfo resolve(@NotNull JALParser.JvmInsArgLocalRefContext localRef, @NotNull String callerInsn)
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
            int localIndex = EvaluatorCommons.asInt(localNumber);
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
            int localIndex = EvaluatorCommons.asInt(localNumber);
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
            int localIndex = EvaluatorCommons.asInt(localNumber);
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

    private void warnLocalPerformance(@NotNull LocalVariableInfo localVar, @NotNull String callerInsn)
    {
        // xLOAD_<n> が定義されているので，代わりにそっちを使ったほうが効率が良い(e.g. iload_1)
        this.context.postWarning(String.format(
                "Local variable %s is accessed in instruction '%s', " +
                        "but it is recommended to use %s_%d instead for better performance.",
                localVar.name(), callerInsn, localVar.name(), localVar.index()
        ));
    }
}
