package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

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
