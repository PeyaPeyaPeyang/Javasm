package tokyo.peya.javasm.langjal.compiler.instructions.calc.xadd;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorFAdd extends AbstractSingleInstructionEvaluator<JALParser.JvmInsFaddContext>
{
    public InstructionEvaluatorFAdd()
    {
        super(EOpcodes.FADD);
    }

    @Override
    protected JALParser.JvmInsFaddContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFadd();
    }
}
