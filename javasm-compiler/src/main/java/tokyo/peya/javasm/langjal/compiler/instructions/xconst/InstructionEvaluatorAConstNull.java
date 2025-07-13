package tokyo.peya.javasm.langjal.compiler.instructions.xconst;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorAConstNull
        extends AbstractSingleInstructionEvaluator<JALParser.JvmInsAconstNullContext>
{
    public InstructionEvaluatorAConstNull()
    {
        super(EOpcodes.ACONST_NULL);
    }

    @Override
    public FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .pushNull()
                                  .build();
    }

    @Override
    protected JALParser.JvmInsAconstNullContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAconstNull();
    }
}
