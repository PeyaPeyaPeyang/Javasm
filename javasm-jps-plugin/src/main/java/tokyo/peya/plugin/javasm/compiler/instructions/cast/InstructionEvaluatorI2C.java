package tokyo.peya.plugin.javasm.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorI2C extends AbstractSingleInstructionEvaluator<JALParser.JvmInsI2CContext>
{
    public InstructionEvaluatorI2C()
    {
        super(EOpcodes.I2C);
    }

    @Override
    protected JALParser.JvmInsI2CContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsI2C();
    }
}
