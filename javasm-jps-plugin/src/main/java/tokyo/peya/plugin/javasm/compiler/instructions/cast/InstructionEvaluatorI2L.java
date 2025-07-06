package tokyo.peya.plugin.javasm.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorI2L extends AbstractSingleInstructionEvaluator<JALParser.JvmInsI2LContext>
{
    public InstructionEvaluatorI2L()
    {
        super(EOpcodes.I2L);
    }

    @Override
    protected JALParser.JvmInsI2LContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsI2L();
    }
}
