package tokyo.peya.javasm.langjal.compiler.instructions.calc;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

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
