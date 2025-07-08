package tokyo.peya.javasm.langjal.compiler.instructions.calc.xand;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIAnd extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIandContext>
{
    public InstructionEvaluatorIAnd()
    {
        super(EOpcodes.IAND);
    }

    @Override
    protected JALParser.JvmInsIandContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIand();
    }
}
