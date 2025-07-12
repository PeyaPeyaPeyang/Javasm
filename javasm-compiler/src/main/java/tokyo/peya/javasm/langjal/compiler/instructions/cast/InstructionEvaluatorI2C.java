package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
