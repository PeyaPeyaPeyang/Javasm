package tokyo.peya.plugin.javasm.compiler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

import java.util.ArrayList;
import java.util.List;

public class JALMethodEvaluator
{
    private final ClassNode clazz;
    private final MethodNode method;
    private final List<LabelInfo> labels;
    private final List<InstructionInfo> instructions;

    private int bytecodeOffset;

    public JALMethodEvaluator(@NotNull ClassNode cn)
    {
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

        LabelInfo lastLabel = null;
        for (JALParser.MethodBodyItemContext bodyItem: body.methodBodyItem())
        {
            if (bodyItem.label() != null)
                lastLabel = this.evaluateLabel(bodyItem.label());
            else if (bodyItem.instruction() != null)
            {
                this.evaluateInstruction(bodyItem.instruction(), lastLabel);
                lastLabel = null;
            }
        }

        if (this.instructions.isEmpty() || this.instructions.getLast().opcode != Opcodes.RETURN)
        {
            // 最後にRETURNがない場合は、デフォルトでRETURNを追加
            this.addInstruction(new InstructionInfo(Opcodes.RETURN, this.bytecodeOffset));
        }
        
        this.method.visitEnd();
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

    @NotNull
    private InstructionInfo evaluateInstruction(@NotNull JALParser.InstructionContext instruction,
                                                       @Nullable LabelInfo lastLabel)


    {
        InstructionInfo evaluatedInstruction = this.evaluateSingleInstruction(instruction, lastLabel);

        if (evaluatedInstruction != null)
        {
            this.addInstruction(evaluatedInstruction);
            return evaluatedInstruction;
        }

        throw new IllegalArgumentException("Unsupported instruction: " + instruction.getText() + " at offset " + this.bytecodeOffset);
    }

    private void addInstruction(@NotNull InstructionInfo instruction)
    {
        this.instructions.add(instruction);
        this.bytecodeOffset += instruction.getInstructionSize();
        this.method.visitInsn(instruction.opcode);
    }

    @Nullable
    private InstructionInfo evaluateSingleInstruction(
            @NotNull JALParser.InstructionContext instruction,
            @Nullable LabelInfo lastLabel
    )
    {
        if (instruction.jvmInsAaload() != null)
            this.method.visitInsn(Opcodes.AALOAD);
        else if (instruction.jvmInsNop() != null)
            return this.visitSingle(Opcodes.NOP, lastLabel);


        return null;
    }

    private InstructionInfo visitSingle(int opCode, @Nullable LabelInfo lastLabel)
    {
        InstructionInfo inst = new InstructionInfo(opCode, this.bytecodeOffset, lastLabel);
        if (inst.getInstructionSize() != 1)
            throw new IllegalArgumentException(
                    "Instruction size mismatch: expected 1, but got " + inst.getInstructionSize() + " for opcode " + opCode);

        return inst;
    }

    private static int asAccess(JALParser.AccModMethodContext methodNode)
    {
        int accessor = EvaluatorCommons.asAccessLevel(methodNode.accessLevel());
        for (JALParser.AccAttrMethodContext ctxt: methodNode.accAttrMethod())
        {
            if (ctxt.KWD_ACC_ATTR_STATIC() != null)
                accessor |= Opcodes.ACC_STATIC;
            else if (ctxt.KWD_ACC_ATTR_FINAL() != null)
                accessor |= Opcodes.ACC_FINAL;
            else if (ctxt.KWD_ACC_ATTR_SYNCHRONIZED() != null)
                accessor |= Opcodes.ACC_SYNCHRONIZED;
            else if (ctxt.KWD_ACC_ATTR_BRIDGE() != null)
                accessor |= Opcodes.ACC_BRIDGE;
            else if (ctxt.KWD_ACC_ATTR_VARARGS() != null)
                accessor |= Opcodes.ACC_VARARGS;
            else if (ctxt.KWD_ACC_ATTR_NATIVE() != null)
                accessor |= Opcodes.ACC_NATIVE;
            else if (ctxt.KWD_ACC_ATTR_ABSTRACT() != null)
                accessor |= Opcodes.ACC_ABSTRACT;
            else if (ctxt.KWD_ACC_ATTR_STRICTFP() != null)
                accessor |= Opcodes.ACC_STRICT;
            else if (ctxt.KWD_ACC_ATTR_SYNTHETIC() != null)
                accessor |= Opcodes.ACC_SYNTHETIC;
        }

        return accessor;
    }

    private record LabelInfo(
            @NotNull String labelName,
            @NotNull Label label,
            int bytecodeOffset
    ) { }

    private record InstructionInfo(
            int opcode,
            int bytecodeOffset,
            @Nullable
            LabelInfo assignedLabel,
            int customSize // wide, lookupswitch, tableswitch, など
    )
    {
        InstructionInfo(int opcode, int bytecodeOffset, @Nullable LabelInfo assignedLabel)
        {
            this(opcode, bytecodeOffset, assignedLabel, -1);
        }

        InstructionInfo(int opcode, int bytecodeOffset)
        {
            this(opcode, bytecodeOffset, null, -1);
        }


        public  int getInstructionSize()
        {
            if (this.customSize > 0)
                return this.customSize;  // 明確に指定されたサイズを返す
            return switch (this.opcode)
            {
                case Opcodes.NOP, Opcodes.ACONST_NULL, Opcodes.ICONST_M1, Opcodes.ICONST_0,
                     Opcodes.ICONST_1, Opcodes.ICONST_2, Opcodes.ICONST_3, Opcodes.ICONST_4,
                     Opcodes.ICONST_5, Opcodes.LCONST_0, Opcodes.LCONST_1, Opcodes.FCONST_0,
                     Opcodes.FCONST_1, Opcodes.FCONST_2, Opcodes.DCONST_0, Opcodes.DCONST_1,
                     /* iload_0: */ 26, /* iload_1: */ 27, /* iload_2: */ 28, /* iload_3: */ 29,
                     /* lload_0: */ 30, /* lload_1: */ 31, /* lload_2: */ 32, /* lload_3: */ 33,
                     /* fload_0: */ 34, /* fload_1: */ 35, /* fload_2: */ 36, /* fload_3: */ 37,
                     /* dload_0: */ 38, /* dload_1: */ 39, /* dload_2: */ 40, /* dload_3: */ 41,
                     /* aload_0: */ 42, /* aload_1: */ 43, /* aload_2: */ 44, /* aload_3: */ 45,
                     Opcodes.IALOAD, Opcodes.LALOAD, Opcodes.FALOAD, Opcodes.DALOAD, Opcodes.AALOAD,
                     Opcodes.BALOAD, Opcodes.CALOAD, Opcodes.SALOAD,
                     /* istore_0: */ 59, /* istore_1: */ 60, /* istore_2: */ 61, /* istore_3: */ 62,
                     /* lstore_0: */ 63, /* lstore_1: */ 64, /* lstore_2: */ 65, /* lstore_3: */ 66,
                     /* fstore_0: */ 67, /* fstore_1: */ 68, /* fstore_2: */ 69, /* fstore_3: */ 70,
                     /* dstore_0: */ 71, /* dstore_1: */ 72, /* dstore_2: */ 73, /* dstore_3: */ 74,
                     /* astore_0: */ 75, /* astore_1: */ 76, /* astore_2: */ 77, /* astore_3: */ 78,
                     Opcodes.IASTORE, Opcodes.LASTORE,
                     Opcodes.FASTORE, Opcodes.DASTORE, Opcodes.AASTORE, Opcodes.BASTORE, Opcodes.CASTORE,
                     Opcodes.SASTORE, Opcodes.POP, Opcodes.POP2, Opcodes.DUP, Opcodes.DUP_X1, Opcodes.DUP_X2,
                     Opcodes.DUP2, Opcodes.DUP2_X1, Opcodes.DUP2_X2, Opcodes.SWAP, Opcodes.IADD,
                     Opcodes.LADD, Opcodes.FADD, Opcodes.DADD, Opcodes.ISUB, Opcodes.LSUB,
                     Opcodes.FSUB, Opcodes.DSUB, Opcodes.IMUL, Opcodes.LMUL, Opcodes.FMUL,
                     Opcodes.DMUL, Opcodes.IDIV, Opcodes.LDIV, Opcodes.FDIV, Opcodes.DDIV,
                     Opcodes.IREM, Opcodes.LREM, Opcodes.FREM, Opcodes.DREM, Opcodes.INEG,
                     Opcodes.LNEG, Opcodes.FNEG, Opcodes.DNEG, Opcodes.ISHL, Opcodes.LSHL,
                     Opcodes.ISHR, Opcodes.LSHR, Opcodes.IUSHR, Opcodes.LUSHR, Opcodes.IAND,
                     Opcodes.LAND, Opcodes.IOR, Opcodes.LOR, Opcodes.IXOR, Opcodes.LXOR,
                     Opcodes.IINC, Opcodes.I2L, Opcodes.I2F, Opcodes.I2D, Opcodes.L2I,
                     Opcodes.L2F, Opcodes.L2D, Opcodes.F2I, Opcodes.F2L, Opcodes.F2D,
                     Opcodes.D2I, Opcodes.D2L, Opcodes.D2F, Opcodes.I2B, Opcodes.I2C,
                     Opcodes.I2S, Opcodes.LCMP, Opcodes.FCMPL, Opcodes.FCMPG, Opcodes.DCMPL,
                     Opcodes.DCMPG, Opcodes.IRETURN, Opcodes.LRETURN, Opcodes.FRETURN,
                     Opcodes.DRETURN, Opcodes.ARETURN, Opcodes.RETURN, Opcodes.ARRAYLENGTH,
                     Opcodes.ATHROW, Opcodes.MONITORENTER, Opcodes.MONITOREXIT -> 1;
                case Opcodes.BIPUSH, Opcodes.LDC, Opcodes.ILOAD, Opcodes.LLOAD, Opcodes.FLOAD,
                     Opcodes.DLOAD, Opcodes.ALOAD, Opcodes.ISTORE, Opcodes.LSTORE,
                     Opcodes.FSTORE, Opcodes.DSTORE, Opcodes.ASTORE, Opcodes.RET -> 2;
                case Opcodes.SIPUSH, /* ldc_w: */ 19, /* ldc2_w: */ 20, Opcodes.GETSTATIC,
                     Opcodes.PUTSTATIC, Opcodes.GETFIELD, Opcodes.PUTFIELD, Opcodes.INVOKEVIRTUAL,
                     Opcodes.INVOKESPECIAL, Opcodes.INVOKESTATIC, Opcodes.NEW, Opcodes.ANEWARRAY,
                     Opcodes.CHECKCAST, Opcodes.INSTANCEOF, Opcodes.IFEQ, Opcodes.IFNE,
                     Opcodes.IFLT, Opcodes.IFGE, Opcodes.IFGT, Opcodes.IFLE,
                     Opcodes.IF_ICMPEQ, Opcodes.IF_ICMPNE, Opcodes.IF_ICMPLT, Opcodes.IF_ICMPGE,
                     Opcodes.IF_ICMPGT, Opcodes.IF_ICMPLE, Opcodes.IF_ACMPEQ, Opcodes.IF_ACMPNE,
                     Opcodes.IFNULL, Opcodes.IFNONNULL, Opcodes.GOTO, Opcodes.JSR -> 3;
                case Opcodes.MULTIANEWARRAY -> 4;
                /* case Opcodes.TABLESWITCH, Opcodes.LOOKUPSWITCH, wide: 196, Opcodes.INVOKEDYNAMIC, Opcodes.INVOKEINTERFACE ->*/
                default -> throw new IllegalArgumentException(
                        "Unable to determine instruction size for opcode(" + this.opcode + "). " +
                                "This is variable-sized or not supported yet.");
            };
        }
    }

}
