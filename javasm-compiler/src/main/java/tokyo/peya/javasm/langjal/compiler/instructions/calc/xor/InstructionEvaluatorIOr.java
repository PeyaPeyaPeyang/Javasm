package tokyo.peya.javasm.langjal.compiler.instructions.calc.xor;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
