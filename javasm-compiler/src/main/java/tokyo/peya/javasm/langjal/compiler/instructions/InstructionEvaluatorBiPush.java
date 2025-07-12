package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.IntInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;
import tokyo.peya.javasm.langjal.compiler.utils.EvaluatorCommons;

public class InstructionEvaluatorBiPush extends AbstractInstructionEvaluator<JALParser.JvmInsBipushContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
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
    protected JALParser.JvmInsBipushContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsBipush();
    }
}
