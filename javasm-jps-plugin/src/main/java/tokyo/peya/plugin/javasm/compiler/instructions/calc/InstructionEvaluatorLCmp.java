package tokyo.peya.plugin.javasm.compiler.instructions.calc;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLCmp extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLcmpContext>
{
    public InstructionEvaluatorLCmp()
    {
        super(EOpcodes.LCMP);
    }

    @Override
    protected JALParser.JvmInsLcmpContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLcmp();
    }
}
