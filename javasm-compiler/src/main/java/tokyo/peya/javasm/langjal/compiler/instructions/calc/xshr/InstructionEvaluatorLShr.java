package tokyo.peya.javasm.langjal.compiler.instructions.calc.xshr;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLShr extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLshrContext>
{
    public InstructionEvaluatorLShr()
    {
        super(EOpcodes.LSHR);
    }

    @Override
    protected JALParser.JvmInsLshrContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLshr();
    }
}
