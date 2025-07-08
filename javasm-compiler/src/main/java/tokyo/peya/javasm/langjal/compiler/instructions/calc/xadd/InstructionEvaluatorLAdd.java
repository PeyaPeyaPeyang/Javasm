package tokyo.peya.javasm.langjal.compiler.instructions.calc.xadd;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLAdd extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLaddContext>
{
    public InstructionEvaluatorLAdd()
    {
        super(EOpcodes.LADD);
    }

    @Override
    protected JALParser.JvmInsLaddContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLadd();
    }
}
