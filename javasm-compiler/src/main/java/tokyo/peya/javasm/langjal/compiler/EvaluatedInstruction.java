package tokyo.peya.javasm.langjal.compiler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.tree.AbstractInsnNode;

public record EvaluatedInstruction(
        AbstractInsnNode insn,
        int customSize // wide, lookupswitch, tableswitch, など
)
{
    private EvaluatedInstruction(@Nullable AbstractInsnNode insn)
    {
        this(insn, 0);
    }

    public int getInstructionSize()
    {
        if (this.customSize > 0)
            return this.customSize;  // 明確に指定されたサイズを返す
        return switch (this.insn.getOpcode())
        {
            case EOpcodes.NOP, EOpcodes.ACONST_NULL, EOpcodes.ICONST_M1, EOpcodes.ICONST_0,
                 EOpcodes.ICONST_1, EOpcodes.ICONST_2, EOpcodes.ICONST_3, EOpcodes.ICONST_4,
                 EOpcodes.ICONST_5, EOpcodes.LCONST_0, EOpcodes.LCONST_1, EOpcodes.FCONST_0,
                 EOpcodes.FCONST_1, EOpcodes.FCONST_2, EOpcodes.DCONST_0, EOpcodes.DCONST_1,
                 EOpcodes.ILOAD_0, EOpcodes.ILOAD_1, EOpcodes.ILOAD_2, EOpcodes.ILOAD_3,
                 EOpcodes.LLOAD_0, EOpcodes.LLOAD_1, EOpcodes.LLOAD_2, EOpcodes.LLOAD_3,
                 EOpcodes.FLOAD_0, EOpcodes.FLOAD_1, EOpcodes.FLOAD_2, EOpcodes.FLOAD_3,
                 EOpcodes.DLOAD_0, EOpcodes.DLOAD_1, EOpcodes.DLOAD_2, EOpcodes.DLOAD_3,
                 EOpcodes.ALOAD_0, EOpcodes.ALOAD_1, EOpcodes.ALOAD_2, EOpcodes.ALOAD_3,
                 EOpcodes.IALOAD, EOpcodes.LALOAD, EOpcodes.FALOAD, EOpcodes.DALOAD, EOpcodes.AALOAD,
                 EOpcodes.BALOAD, EOpcodes.CALOAD, EOpcodes.SALOAD,
                 EOpcodes.ISTORE_0, EOpcodes.ISTORE_1, EOpcodes.ISTORE_2, EOpcodes.ISTORE_3,
                 EOpcodes.LSTORE_0, EOpcodes.LSTORE_1, EOpcodes.LSTORE_2, EOpcodes.LSTORE_3,
                 EOpcodes.FSTORE_0, EOpcodes.FSTORE_1, EOpcodes.FSTORE_2, EOpcodes.FSTORE_3,
                 EOpcodes.DSTORE_0, EOpcodes.DSTORE_1, EOpcodes.DSTORE_2, EOpcodes.DSTORE_3,
                 EOpcodes.ASTORE_0, EOpcodes.ASTORE_1, EOpcodes.ASTORE_2, EOpcodes.ASTORE_3,
                 EOpcodes.IASTORE, EOpcodes.LASTORE,
                 EOpcodes.FASTORE, EOpcodes.DASTORE, EOpcodes.AASTORE, EOpcodes.BASTORE, EOpcodes.CASTORE,
                 EOpcodes.SASTORE, EOpcodes.POP, EOpcodes.POP2, EOpcodes.DUP, EOpcodes.DUP_X1, EOpcodes.DUP_X2,
                 EOpcodes.DUP2, EOpcodes.DUP2_X1, EOpcodes.DUP2_X2, EOpcodes.SWAP, EOpcodes.IADD,
                 EOpcodes.LADD, EOpcodes.FADD, EOpcodes.DADD, EOpcodes.ISUB, EOpcodes.LSUB,
                 EOpcodes.FSUB, EOpcodes.DSUB, EOpcodes.IMUL, EOpcodes.LMUL, EOpcodes.FMUL,
                 EOpcodes.DMUL, EOpcodes.IDIV, EOpcodes.LDIV, EOpcodes.FDIV, EOpcodes.DDIV,
                 EOpcodes.IREM, EOpcodes.LREM, EOpcodes.FREM, EOpcodes.DREM, EOpcodes.INEG,
                 EOpcodes.LNEG, EOpcodes.FNEG, EOpcodes.DNEG, EOpcodes.ISHL, EOpcodes.LSHL,
                 EOpcodes.ISHR, EOpcodes.LSHR, EOpcodes.IUSHR, EOpcodes.LUSHR, EOpcodes.IAND,
                 EOpcodes.LAND, EOpcodes.IOR, EOpcodes.LOR, EOpcodes.IXOR, EOpcodes.LXOR,
                 EOpcodes.IINC, EOpcodes.I2L, EOpcodes.I2F, EOpcodes.I2D, EOpcodes.L2I,
                 EOpcodes.L2F, EOpcodes.L2D, EOpcodes.F2I, EOpcodes.F2L, EOpcodes.F2D,
                 EOpcodes.D2I, EOpcodes.D2L, EOpcodes.D2F, EOpcodes.I2B, EOpcodes.I2C,
                 EOpcodes.I2S, EOpcodes.LCMP, EOpcodes.FCMPL, EOpcodes.FCMPG, EOpcodes.DCMPL,
                 EOpcodes.DCMPG, EOpcodes.IRETURN, EOpcodes.LRETURN, EOpcodes.FRETURN,
                 EOpcodes.DRETURN, EOpcodes.ARETURN, EOpcodes.RETURN, EOpcodes.ARRAYLENGTH,
                 EOpcodes.ATHROW, EOpcodes.MONITORENTER, EOpcodes.MONITOREXIT -> 1;
            case EOpcodes.BIPUSH, EOpcodes.LDC, EOpcodes.ILOAD, EOpcodes.LLOAD, EOpcodes.FLOAD,
                 EOpcodes.DLOAD, EOpcodes.ALOAD, EOpcodes.ISTORE, EOpcodes.LSTORE,
                 EOpcodes.FSTORE, EOpcodes.DSTORE, EOpcodes.ASTORE, EOpcodes.RET -> 2;
            case EOpcodes.SIPUSH, EOpcodes.LDC_W, EOpcodes.LDC2_W, EOpcodes.GETSTATIC,
                 EOpcodes.PUTSTATIC, EOpcodes.GETFIELD, EOpcodes.PUTFIELD, EOpcodes.INVOKEVIRTUAL,
                 EOpcodes.INVOKESPECIAL, EOpcodes.INVOKESTATIC, EOpcodes.NEW, EOpcodes.ANEWARRAY,
                 EOpcodes.CHECKCAST, EOpcodes.INSTANCEOF, EOpcodes.IFEQ, EOpcodes.IFNE,
                 EOpcodes.IFLT, EOpcodes.IFGE, EOpcodes.IFGT, EOpcodes.IFLE,
                 EOpcodes.IF_ICMPEQ, EOpcodes.IF_ICMPNE, EOpcodes.IF_ICMPLT, EOpcodes.IF_ICMPGE,
                 EOpcodes.IF_ICMPGT, EOpcodes.IF_ICMPLE, EOpcodes.IF_ACMPEQ, EOpcodes.IF_ACMPNE,
                 EOpcodes.IFNULL, EOpcodes.IFNONNULL, EOpcodes.GOTO, EOpcodes.JSR -> 3;
            case EOpcodes.MULTIANEWARRAY -> 4;
            case EOpcodes.INVOKEINTERFACE, EOpcodes.GOTO_W, EOpcodes.JSR_W -> 5;
            /* case EOpcodes.TABLESWITCH, EOpcodes.LOOKUPSWITCH, EOPCodes.WIDE: 196 ->*/
            default -> throw new IllegalArgumentException(
                    "Unable to determine instruction size for opcode(" + this.insn.getOpcode() + "). " +
                            "This is variable-sized or not supported yet.");
        };
    }

    public static EvaluatedInstruction of(@NotNull AbstractInsnNode insn)
    {
        return new EvaluatedInstruction(insn);
    }

    public static EvaluatedInstruction of(@NotNull AbstractInsnNode insn, int size)
    {
        checkSizeProvided(insn.getOpcode(), size);
        return new EvaluatedInstruction(insn, size);
    }

    private static void checkSizeProvided(int opcode, int size)
    {
        if (size > 0)
            return;

        if (opcode == EOpcodes.TABLESWITCH
                || opcode == EOpcodes.LOOKUPSWITCH
                || opcode == EOpcodes.WIDE
                || opcode == EOpcodes.INVOKEDYNAMIC)
            throw new IllegalArgumentException("Instruction with opcode(" + opcode + ") requires a custom size to be specified.");
    }
}
