package tokyo.peya.javasm.langjal.compiler.instructions.calc.xsub;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorISub extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIsubContext>
{
    public InstructionEvaluatorISub()
    {
        super(EOpcodes.ISUB);
    }

    @Override
    protected JALParser.JvmInsIsubContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIsub();
    }
}
