package tokyo.peya.plugin.javasm.compiler.instructions.xstore;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.VarInsnNode;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.compiler.LocalVariableInfo;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

@UtilityClass
public class InstructionEvaluateHelperXStore
{
    public static @NotNull EvaluatedInstruction evaluate(int opcode, @NotNull JALMethodEvaluator evaluator,
                                                     @NotNull JALParser.JvmInsArgLocalRefContext localRef, @NotNull String type)
    {
        LocalVariableInfo registeredLocal = evaluator.resolveSafe(localRef);
        if (registeredLocal == null)
            registeredLocal = evaluator.registerLocal(localRef, type);  // 宣言時

        VarInsnNode insn = new VarInsnNode(opcode, registeredLocal.index());
        return EvaluatedInstruction.of(insn);
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
