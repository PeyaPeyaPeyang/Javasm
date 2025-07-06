package tokyo.peya.plugin.javasm.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorF2D extends AbstractSingleInstructionEvaluator<JALParser.JvmInsF2DContext>
{
    public InstructionEvaluatorF2D()
    {
        super(EOpcodes.F2D);
    }

    @Override
    protected JALParser.@NotNull JvmInsF2DContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsF2D();
    }
}
