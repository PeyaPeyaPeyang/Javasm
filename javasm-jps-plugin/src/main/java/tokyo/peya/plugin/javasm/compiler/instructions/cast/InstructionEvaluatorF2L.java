package tokyo.peya.plugin.javasm.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorF2L extends AbstractSingleInstructionEvaluator<JALParser.JvmInsF2LContext>
{
    public InstructionEvaluatorF2L()
    {
        super(EOpcodes.F2L);
    }

    @Override
    protected JALParser.JvmInsF2LContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsF2L();
    }
}
