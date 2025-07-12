package tokyo.peya.javasm.langjal.compiler.instructions.calc.xadd;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
