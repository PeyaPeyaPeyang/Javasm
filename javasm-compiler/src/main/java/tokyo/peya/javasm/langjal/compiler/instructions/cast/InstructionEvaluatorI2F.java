package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorI2F extends AbstractSingleInstructionEvaluator<JALParser.JvmInsI2FContext>
{
    public InstructionEvaluatorI2F()
    {
        super(EOpcodes.I2F);
    }

    @Override
    protected JALParser.JvmInsI2FContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsI2F();
    }
}
