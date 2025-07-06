package tokyo.peya.plugin.javasm.compiler.instructions.calc.xshr;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLShr extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLshrContext>
{
    public InstructionEvaluatorLShr()
    {
        super(EOpcodes.LSHR);
    }

    @Override
    protected JALParser.@NotNull JvmInsLshrContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLshr();
    }
}
