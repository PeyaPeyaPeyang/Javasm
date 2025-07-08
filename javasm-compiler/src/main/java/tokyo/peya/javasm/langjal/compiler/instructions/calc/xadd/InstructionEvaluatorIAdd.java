package tokyo.peya.javasm.langjal.compiler.instructions.calc.xadd;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIAdd extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIaddContext>
{
    public InstructionEvaluatorIAdd()
    {
        super(EOpcodes.IADD);
    }

    @Override
    protected JALParser.JvmInsIaddContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIadd();
    }
}
