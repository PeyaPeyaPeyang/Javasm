package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.IntInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.exceptions.IllegalInstructionException;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;
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
            throw new IllegalInstructionException(
                    "Value out of range for bipush: " + value + ", expected " + Byte.MIN_VALUE + " ~ " + Byte.MAX_VALUE,
                    ctxt.NUMBER()
            );
        return EvaluatedInstruction.of(this, new IntInsnNode(EOpcodes.BIPUSH, value));
    }

    @Override
    public FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .pushPrimitive(StackElementType.INTEGER)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsBipushContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsBipush();
    }
}
