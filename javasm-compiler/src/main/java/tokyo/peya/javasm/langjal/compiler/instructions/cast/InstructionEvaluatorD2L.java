package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
