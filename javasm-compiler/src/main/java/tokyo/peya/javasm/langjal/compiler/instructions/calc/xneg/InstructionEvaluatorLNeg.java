package tokyo.peya.javasm.langjal.compiler.instructions.calc.xneg;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
