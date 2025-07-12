package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorI2L extends AbstractSingleInstructionEvaluator<JALParser.JvmInsI2LContext>
{
    public InstructionEvaluatorI2L()
    {
        super(EOpcodes.I2L);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popPrimitive(StackElementType.INTEGER)
                                  .pushPrimitive(StackElementType.LONG)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsI2LContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsI2L();
    }
}
