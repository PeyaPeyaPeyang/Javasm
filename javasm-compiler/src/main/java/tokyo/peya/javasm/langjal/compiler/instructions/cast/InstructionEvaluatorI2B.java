package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
