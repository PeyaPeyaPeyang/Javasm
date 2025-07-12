package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.IntInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;
import tokyo.peya.javasm.langjal.compiler.utils.EvaluatorCommons;

public class InstructionEvaluatorSiPush extends AbstractInstructionEvaluator<JALParser.JvmInsSipushContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
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
