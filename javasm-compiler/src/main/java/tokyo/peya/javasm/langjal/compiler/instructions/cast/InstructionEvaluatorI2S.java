package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorI2S extends AbstractSingleInstructionEvaluator<JALParser.JvmInsI2SContext>
{
    public InstructionEvaluatorI2S()
    {
        super(EOpcodes.I2S);
    }

    @Override
    protected JALParser.JvmInsI2SContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsI2S();
    }
}
