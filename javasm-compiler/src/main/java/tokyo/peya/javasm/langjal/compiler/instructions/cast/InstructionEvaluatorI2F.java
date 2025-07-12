package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
