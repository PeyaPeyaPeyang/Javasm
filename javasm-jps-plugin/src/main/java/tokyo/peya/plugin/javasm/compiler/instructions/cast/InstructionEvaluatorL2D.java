package tokyo.peya.plugin.javasm.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorL2D extends AbstractSingleInstructionEvaluator<JALParser.JvmInsL2DContext>
{
    public InstructionEvaluatorL2D()
    {
        super(EOpcodes.L2D);
    }

    @Override
    protected JALParser.JvmInsL2DContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsL2D();
    }
}
