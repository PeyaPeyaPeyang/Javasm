package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorD2L extends AbstractSingleInstructionEvaluator<JALParser.JvmInsD2LContext>
{
    public InstructionEvaluatorD2L()
    {
        super(EOpcodes.D2L);
    }

    @Override
    protected JALParser.JvmInsD2LContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsD2L();
    }
}
