package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorI2F extends AbstractSingleInstructionEvaluator<JALParser.JvmInsI2FContext>
{
    public InstructionEvaluatorI2F()
    {
        super(EOpcodes.I2F);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popPrimitive(StackElementType.INTEGER)
                                  .pushPrimitive(StackElementType.FLOAT)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsI2FContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsI2F();
    }
}
