package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorAThrow extends AbstractSingleInstructionEvaluator<JALParser.JvmInsAthrowContext>
{
    public InstructionEvaluatorAThrow()
    {
        super(EOpcodes.ATHROW);
    }

    @Override
    public FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popObjectRef(TypeDescriptor.parse("Ljava/lang/Throwable;"))
                                  .build();
    }

    @Override
    protected JALParser.JvmInsAthrowContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAthrow();
    }
}
