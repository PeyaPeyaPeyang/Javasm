package tokyo.peya.plugin.javasm.compiler.instructions.calc.xsub;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

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
