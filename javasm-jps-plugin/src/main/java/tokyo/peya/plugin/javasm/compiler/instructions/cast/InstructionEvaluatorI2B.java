package tokyo.peya.plugin.javasm.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorI2B extends AbstractSingleInstructionEvaluator<JALParser.JvmInsI2BContext>
{
    public InstructionEvaluatorI2B()
    {
        super(EOpcodes.I2B);
    }

    @Override
    protected JALParser.JvmInsI2BContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsI2B();
    }
}
