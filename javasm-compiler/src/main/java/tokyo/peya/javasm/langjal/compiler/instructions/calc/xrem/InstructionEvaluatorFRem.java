package tokyo.peya.javasm.langjal.compiler.instructions.calc.xrem;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorFRem extends AbstractSingleInstructionEvaluator<JALParser.JvmInsFremContext>
{
    public InstructionEvaluatorFRem()
    {
        super(EOpcodes.FREM);
    }

    @Override
    protected JALParser.JvmInsFremContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFrem();
    }
}
