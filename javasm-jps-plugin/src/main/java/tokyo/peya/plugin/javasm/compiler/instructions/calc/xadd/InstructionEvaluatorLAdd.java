package tokyo.peya.plugin.javasm.compiler.instructions.calc.xadd;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLAdd extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLaddContext>
{
    public InstructionEvaluatorLAdd()
    {
        super(EOpcodes.LADD);
    }

    @Override
    protected JALParser.@NotNull JvmInsLaddContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLadd();
    }
}
