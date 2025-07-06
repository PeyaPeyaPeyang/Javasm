package tokyo.peya.plugin.javasm.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.IntInsnNode;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.EvaluatorCommons;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorSiPush extends AbstractInstructionEvaluator<JALParser.JvmInsSipushContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsSipushContext ctxt)
    {
        int value = EvaluatorCommons.asInteger(ctxt.NUMBER());
        if (value < Short.MIN_VALUE || value > Short.MAX_VALUE)
            throw new IllegalArgumentException(
                    "Value out of range for sipush: " + value + ", expected " + Short.MIN_VALUE + " ~ " + Short.MAX_VALUE
            );

        IntInsnNode insn = new IntInsnNode(EOpcodes.SIPUSH, value);
        return EvaluatedInstruction.of(insn);
    }

    @Override
    protected JALParser.JvmInsSipushContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsSipush();
    }
}
