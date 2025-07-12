package tokyo.peya.javasm.langjal.compiler.instructions.calc.xsub;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorDSub extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDsubContext>
{
    public InstructionEvaluatorDSub()
    {
        super(EOpcodes.DSUB);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popPrimitive(StackElementType.DOUBLE)
                                  .popPrimitive(StackElementType.DOUBLE)
                                  .pushPrimitive(StackElementType.DOUBLE)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsDsubContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDsub();
    }
}
