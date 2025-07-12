package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorD2L extends AbstractSingleInstructionEvaluator<JALParser.JvmInsD2LContext>
{
    public InstructionEvaluatorD2L()
    {
        super(EOpcodes.D2L);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popPrimitive(StackElementType.DOUBLE)
                                  .pushPrimitive(StackElementType.INTEGER)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsD2LContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsD2L();
    }
}
