package tokyo.peya.plugin.javasm.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorI2S extends AbstractSingleInstructionEvaluator<JALParser.JvmInsI2SContext>
{
    public InstructionEvaluatorI2S()
    {
        super(EOpcodes.I2S);
    }

    @Override
    protected JALParser.@NotNull JvmInsI2SContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsI2S();
    }
}
