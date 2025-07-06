package tokyo.peya.plugin.javasm.compiler.instructions.xload;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.VarInsnNode;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.compiler.LocalVariableInfo;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluateHelperXLoad
{
    public static @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                         JALParser.JvmInsArgLocalRefContext ref,
                                                         int opcode,
                                                         @NotNull String callerInsn)
    {
        LocalVariableInfo local = evaluator.resolve(ref, callerInsn);

        VarInsnNode insn = new VarInsnNode(opcode, local.index());
        return EvaluatedInstruction.of(insn);
    }

    public static @NotNull EvaluatedInstruction evaluateN(@NotNull JALMethodEvaluator evaluator, int opcode, int idx)
    {
        LocalVariableInfo local = evaluator.resolveLocalSafe(idx);
        if (local == null)
            throw new IllegalArgumentException("Local variable with index " + idx + " is not defined in the current method context.");

        InsnNode insn = new InsnNode(opcode); // ここには iload_0, iload_1, iload_2, iload_3 などの短い命令が入る
        return EvaluatedInstruction.of(insn);
    }
}
