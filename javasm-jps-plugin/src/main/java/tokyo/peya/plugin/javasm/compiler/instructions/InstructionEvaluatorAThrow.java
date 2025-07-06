package tokyo.peya.plugin.javasm.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

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
