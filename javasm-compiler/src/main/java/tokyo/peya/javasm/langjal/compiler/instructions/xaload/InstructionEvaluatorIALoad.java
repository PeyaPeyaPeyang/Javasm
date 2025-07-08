package tokyo.peya.javasm.langjal.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIALoad extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIaloadContext>
{
    public InstructionEvaluatorIALoad()
    {
        super(EOpcodes.IALOAD);
    }

    @Override
    protected JALParser.JvmInsIaloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIaload();
    }
}
