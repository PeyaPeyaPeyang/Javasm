package tokyo.peya.plugin.javasm.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorD2L extends AbstractSingleInstructionEvaluator<JALParser.JvmInsD2LContext>
{
    public InstructionEvaluatorD2L()
    {
        super(EOpcodes.D2L);
    }

    @Override
    protected JALParser.@NotNull JvmInsD2LContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsD2L();
    }
}
