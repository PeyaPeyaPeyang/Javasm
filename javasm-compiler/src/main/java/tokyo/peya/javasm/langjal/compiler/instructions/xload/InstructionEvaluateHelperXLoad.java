package tokyo.peya.javasm.langjal.compiler.instructions.xload;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.VarInsnNode;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.LocalVariableInfo;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluateHelperXLoad
{
    public static @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                         @NotNull JALParser.JvmInsArgLocalRefContext ref,
                                                         int opcode,
                                                         @NotNull String callerInsn,
                                                         @Nullable TerminalNode wide)
    {
        LocalVariableInfo local = evaluator.resolveLocal(ref, callerInsn);

        int idx = local.index();
        boolean isWide = wide != null;
        if (idx >= 0xFF && !isWide)
            throw new IllegalArgumentException(String.format(
                    "Local variable index %d is too large for %s instruction. Use wide variant with.",
                    idx, callerInsn
            ));

        int size = isWide ? 4: 2;
        VarInsnNode insn = new VarInsnNode(opcode, idx);
        return EvaluatedInstruction.of(insn, size);
    }

    public static @NotNull EvaluatedInstruction evaluateN(@NotNull JALMethodEvaluator evaluator, int opcode, int idx)
    {
        LocalVariableInfo local = evaluator.resolveLocalSafe(idx);
        if (local == null)
            throw new IllegalArgumentException("Local variable with index " + idx + " is not defined in the current method context.");

        VarInsnNode insn = new VarInsnNode(opcode, idx); // ここには iload_0, iload_1, iload_2, iload_3 などの短い命令が入る
        return EvaluatedInstruction.of(insn);
    }
}
