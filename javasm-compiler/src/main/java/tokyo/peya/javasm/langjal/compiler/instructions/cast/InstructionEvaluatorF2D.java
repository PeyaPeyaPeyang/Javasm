package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorF2D extends AbstractSingleInstructionEvaluator<JALParser.JvmInsF2DContext>
{
    public InstructionEvaluatorF2D()
    {
        super(EOpcodes.F2D);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popPrimitive(StackElementType.FLOAT)
                                  .pushPrimitive(StackElementType.DOUBLE)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsF2DContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsF2D();
    }
}
