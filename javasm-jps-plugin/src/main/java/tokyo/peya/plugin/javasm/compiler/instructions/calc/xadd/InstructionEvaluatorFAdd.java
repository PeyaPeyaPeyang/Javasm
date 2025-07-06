package tokyo.peya.plugin.javasm.compiler.instructions.calc.xadd;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorFAdd extends AbstractSingleInstructionEvaluator<JALParser.JvmInsFaddContext>
{
    public InstructionEvaluatorFAdd()
    {
        super(EOpcodes.FADD);
    }

    @Override
    protected JALParser.@NotNull JvmInsFaddContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFadd();
    }
}
