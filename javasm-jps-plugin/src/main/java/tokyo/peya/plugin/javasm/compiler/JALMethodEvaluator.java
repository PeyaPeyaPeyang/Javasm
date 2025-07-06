package tokyo.peya.plugin.javasm.compiler;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Label;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.MethodNode;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

import java.util.ArrayList;
import java.util.List;

public class JALMethodEvaluator
{
    private final EvaluatingContext evaluatingContext;
    private final ClassNode clazz;
    private final MethodNode method;
    private final List<LabelInfo> labels;
    private final List<InstructionInfo> instructions;

    private int bytecodeOffset;

    public JALMethodEvaluator(@NotNull EvaluatingContext ctxt, @NotNull ClassNode cn)
    {
        this.evaluatingContext = ctxt;
        this.clazz = cn;
        this.method = new MethodNode();

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
                        this.evaluatingContext,
                        bodyItem.instruction(),
                        this.bytecodeOffset,
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

}
