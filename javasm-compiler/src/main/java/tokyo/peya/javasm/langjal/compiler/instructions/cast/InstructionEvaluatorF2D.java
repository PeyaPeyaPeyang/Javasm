package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
