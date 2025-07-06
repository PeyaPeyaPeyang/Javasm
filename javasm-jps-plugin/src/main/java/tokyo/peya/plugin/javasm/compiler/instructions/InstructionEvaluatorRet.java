package tokyo.peya.plugin.javasm.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.VarInsnNode;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.EvaluatorCommons;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.compiler.LocalVariableInfo;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorRet extends AbstractInstructionEvaluator<JALParser.JvmInsRetContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsRetContext ctxt)
    {
        LocalVariableInfo local = evaluator.resolve(ctxt.jvmInsArgLocalRef(), "ret");

        VarInsnNode insn = new VarInsnNode(EOpcodes.RET, local.index());
        return EvaluatedInstruction.of(insn);
    }

    @Override
    protected JALParser.@NotNull JvmInsRetContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsRet();
    }
}
