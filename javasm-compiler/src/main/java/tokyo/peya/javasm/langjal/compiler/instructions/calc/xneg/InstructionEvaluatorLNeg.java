package tokyo.peya.javasm.langjal.compiler.instructions.calc.xneg;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLNeg extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLnegContext>
{
    public InstructionEvaluatorLNeg()
    {
        super(EOpcodes.LREM);
    }

    @Override
    protected JALParser.JvmInsLnegContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLneg();
    }
}
