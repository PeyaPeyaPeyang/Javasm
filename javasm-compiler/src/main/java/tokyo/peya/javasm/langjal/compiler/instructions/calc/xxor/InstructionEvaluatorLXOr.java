package tokyo.peya.javasm.langjal.compiler.instructions.calc.xxor;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLXOr extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLxorContext>
{
    public InstructionEvaluatorLXOr()
    {
        super(EOpcodes.LXOR);
    }

    @Override
    protected JALParser.JvmInsLxorContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLxor();
    }
}
