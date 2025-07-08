package tokyo.peya.javasm.langjal.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLALoad extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLaloadContext>
{
    public InstructionEvaluatorLALoad()
    {
        super(EOpcodes.LALOAD);
    }

    @Override
    protected JALParser.JvmInsLaloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLaload();
    }
}
