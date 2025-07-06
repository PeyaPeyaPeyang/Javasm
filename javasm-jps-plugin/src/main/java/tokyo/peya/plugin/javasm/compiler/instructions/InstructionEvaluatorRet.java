package tokyo.peya.plugin.javasm.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.VarInsnNode;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.compiler.LocalVariableInfo;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorRet extends AbstractInstructionEvaluator<JALParser.JvmInsRetContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsRetContext ctxt)
    {
        LocalVariableInfo local = evaluator.resolveLocal(ctxt.jvmInsArgLocalRef(), "ret");

        int idx = local.index();
        boolean isWide = ctxt.INSN_WIDE() != null;
        if (idx >= 0xFF && !isWide)
            throw new IllegalArgumentException(String.format(
                    "Local variable index %d is too large for ret instruction. Use wide variant with.",
                    idx
            ));

        VarInsnNode insn = new VarInsnNode(EOpcodes.RET, local.index());

        int size = isWide ? 4: 2;
        return EvaluatedInstruction.of(insn, size);
    }

    @Override
    protected JALParser.JvmInsRetContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsRet();
    }
}
