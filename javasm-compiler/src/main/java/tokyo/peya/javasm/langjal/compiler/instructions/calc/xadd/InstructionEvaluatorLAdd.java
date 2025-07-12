package tokyo.peya.javasm.langjal.compiler.instructions.calc.xadd;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
