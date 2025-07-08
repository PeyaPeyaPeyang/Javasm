package tokyo.peya.javasm.langjal.compiler.instructions.calc.xneg;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorFNeg extends AbstractSingleInstructionEvaluator<JALParser.JvmInsFnegContext>
{
    public InstructionEvaluatorFNeg()
    {
        super(EOpcodes.FREM);
    }

    @Override
    protected JALParser.JvmInsFnegContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFneg();
    }
}
