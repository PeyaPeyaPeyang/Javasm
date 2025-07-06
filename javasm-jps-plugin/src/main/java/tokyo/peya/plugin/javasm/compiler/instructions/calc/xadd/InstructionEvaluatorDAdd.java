package tokyo.peya.plugin.javasm.compiler.instructions.calc.xadd;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDAdd extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDaddContext>
{
    public InstructionEvaluatorDAdd()
    {
        super(EOpcodes.DADD);
    }

    @Override
    protected JALParser.@NotNull JvmInsDaddContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDadd();
    }
}
