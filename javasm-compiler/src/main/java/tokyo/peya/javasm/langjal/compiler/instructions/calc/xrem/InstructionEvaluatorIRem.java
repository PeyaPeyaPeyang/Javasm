package tokyo.peya.javasm.langjal.compiler.instructions.calc.xrem;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIRem extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIremContext>
{
    public InstructionEvaluatorIRem()
    {
        super(EOpcodes.IREM);
    }

    @Override
    protected JALParser.JvmInsIremContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIrem();
    }
}
