package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.IntInsnNode;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.EvaluatorCommons;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;

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
