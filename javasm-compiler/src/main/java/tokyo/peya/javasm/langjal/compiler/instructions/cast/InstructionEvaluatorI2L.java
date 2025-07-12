package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
