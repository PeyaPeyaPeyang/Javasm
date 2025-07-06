package tokyo.peya.plugin.javasm.compiler.instructions.calc.xadd;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIAdd extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIaddContext>
{
    public InstructionEvaluatorIAdd()
    {
        super(EOpcodes.IADD);
    }

    @Override
    protected JALParser.@NotNull JvmInsIaddContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIadd();
    }
}
