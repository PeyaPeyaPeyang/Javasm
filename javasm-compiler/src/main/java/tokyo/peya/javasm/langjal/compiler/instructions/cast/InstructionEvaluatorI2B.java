package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

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
