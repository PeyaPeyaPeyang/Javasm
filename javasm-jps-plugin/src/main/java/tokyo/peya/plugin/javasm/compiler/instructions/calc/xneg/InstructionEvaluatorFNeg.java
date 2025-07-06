package tokyo.peya.plugin.javasm.compiler.instructions.calc.xneg;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorFNeg extends AbstractSingleInstructionEvaluator<JALParser.JvmInsFnegContext>
{
    public InstructionEvaluatorFNeg()
    {
        super(EOpcodes.FREM);
    }

    @Override
    protected JALParser.@NotNull JvmInsFnegContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFneg();
    }
}
