package tokyo.peya.javasm.langjal.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
