package tokyo.peya.javasm.langjal.compiler.instructions.calc.xxor;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIXOr extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIxorContext>
{
    public InstructionEvaluatorIXOr()
    {
        super(EOpcodes.IXOR);
    }

    @Override
    protected JALParser.JvmInsIxorContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIxor();
    }
}
