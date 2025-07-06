package tokyo.peya.plugin.javasm.compiler.instructions.xstore;

import lombok.experimental.UtilityClass;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.tree.VarInsnNode;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.compiler.LocalVariableInfo;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

@UtilityClass
public class InstructionEvaluateHelperXStore
{
    public static @NotNull EvaluatedInstruction evaluate(int opcode, @NotNull JALMethodEvaluator evaluator,
                                                         @NotNull JALParser.JvmInsArgLocalRefContext localRef,
                                                         @NotNull String type,
                                                         @NotNull String callerInsn,
                                                         @Nullable TerminalNode wide)
    {
        LocalVariableInfo registeredLocal = evaluator.resolveSafe(localRef);
        if (registeredLocal == null)
            registeredLocal = evaluator.registerLocal(localRef, type);  // 宣言時

        int idx = registeredLocal.index();
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

    public static @NotNull EvaluatedInstruction evaluateN(int opcode, int idx,
                                                          @NotNull JALMethodEvaluator evaluator,
                                                          @NotNull String type)
    {
        LocalVariableInfo registeredLocal = evaluator.resolveLocalSafe(idx);
        if (registeredLocal == null)
            registeredLocal = evaluator.registerLocal(idx, type);  // 宣言時

        VarInsnNode insn = new VarInsnNode(opcode, registeredLocal.index());
        return EvaluatedInstruction.of(insn);
    }
}
