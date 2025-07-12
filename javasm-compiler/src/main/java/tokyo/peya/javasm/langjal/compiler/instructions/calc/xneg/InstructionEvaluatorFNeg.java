package tokyo.peya.javasm.langjal.compiler.instructions.calc.xneg;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
