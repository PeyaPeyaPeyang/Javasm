package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorAThrow extends AbstractSingleInstructionEvaluator<JALParser.JvmInsAthrowContext>
{
    public InstructionEvaluatorAThrow()
    {
        super(EOpcodes.ATHROW);
    }

    @Override
    protected JALParser.JvmInsAthrowContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAthrow();
    }
}
