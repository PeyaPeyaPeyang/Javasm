package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorI2D extends AbstractSingleInstructionEvaluator<JALParser.JvmInsI2DContext>
{
    public InstructionEvaluatorI2D()
    {
        super(EOpcodes.I2D);
    }

    @Override
    protected JALParser.JvmInsI2DContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsI2D();
    }
}
