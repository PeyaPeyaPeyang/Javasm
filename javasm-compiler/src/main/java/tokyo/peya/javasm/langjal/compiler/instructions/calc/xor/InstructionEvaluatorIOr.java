package tokyo.peya.javasm.langjal.compiler.instructions.calc.xor;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIOr extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIorContext>
{
    public InstructionEvaluatorIOr()
    {
        super(EOpcodes.IOR);
    }

    @Override
    protected JALParser.JvmInsIorContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIor();
    }
}
