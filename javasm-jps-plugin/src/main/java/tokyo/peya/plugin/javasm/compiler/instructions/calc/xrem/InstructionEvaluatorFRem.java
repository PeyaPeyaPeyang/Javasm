package tokyo.peya.plugin.javasm.compiler.instructions.calc.xrem;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

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
