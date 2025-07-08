package tokyo.peya.javasm.langjal.compiler.instructions.calc.xadd;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDAdd extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDaddContext>
{
    public InstructionEvaluatorDAdd()
    {
        super(EOpcodes.DADD);
    }

    @Override
    protected JALParser.JvmInsDaddContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDadd();
    }
}
