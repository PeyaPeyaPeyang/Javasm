package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorF2L extends AbstractSingleInstructionEvaluator<JALParser.JvmInsF2LContext>
{
    public InstructionEvaluatorF2L()
    {
        super(EOpcodes.F2L);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popPrimitive(StackElementType.FLOAT)
                                  .pushPrimitive(StackElementType.LONG)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsF2LContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsF2L();
    }
}
