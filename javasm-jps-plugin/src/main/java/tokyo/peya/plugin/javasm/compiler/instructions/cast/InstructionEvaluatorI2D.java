package tokyo.peya.plugin.javasm.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorI2D extends AbstractSingleInstructionEvaluator<JALParser.JvmInsI2DContext>
{
    public InstructionEvaluatorI2D()
    {
        super(EOpcodes.I2D);
    }

    @Override
    protected JALParser.@NotNull JvmInsI2DContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsI2D();
    }
}
