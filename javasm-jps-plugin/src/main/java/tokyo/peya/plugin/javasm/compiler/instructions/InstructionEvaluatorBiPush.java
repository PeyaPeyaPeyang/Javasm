package tokyo.peya.plugin.javasm.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.IntInsnNode;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatorCommons;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorBiPush extends AbstractInstructionEvaluator<JALParser.JvmInsBipushContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsBipushContext ctxt)
    {
        int value = EvaluatorCommons.asInteger(ctxt.NUMBER());
        if (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE)
            throw new IllegalArgumentException(
                    "Value out of range for bipush: " + value + ", expected " + Byte.MIN_VALUE + " ~ " + Byte.MAX_VALUE
            );
        return EvaluatedInstruction.of(new IntInsnNode(EOpcodes.BIPUSH, value));
    }

    @Override
    protected JALParser.@NotNull JvmInsBipushContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsBipush();
    }
}
