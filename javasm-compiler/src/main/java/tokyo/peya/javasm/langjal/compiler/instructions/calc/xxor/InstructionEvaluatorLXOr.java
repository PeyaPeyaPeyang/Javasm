package tokyo.peya.javasm.langjal.compiler.instructions.calc.xxor;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
