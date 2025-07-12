package tokyo.peya.javasm.langjal.compiler.instructions.calc.xand;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
