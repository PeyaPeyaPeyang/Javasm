package tokyo.peya.javasm.langjal.compiler.instructions.calc;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
