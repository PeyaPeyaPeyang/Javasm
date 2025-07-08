package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorF2D extends AbstractSingleInstructionEvaluator<JALParser.JvmInsF2DContext>
{
    public InstructionEvaluatorF2D()
    {
        super(EOpcodes.F2D);
    }

    @Override
    protected JALParser.JvmInsF2DContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsF2D();
    }
}
