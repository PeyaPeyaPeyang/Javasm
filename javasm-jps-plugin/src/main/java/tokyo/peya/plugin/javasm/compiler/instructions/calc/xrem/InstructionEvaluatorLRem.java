package tokyo.peya.plugin.javasm.compiler.instructions.calc.xrem;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLRem extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLremContext>
{
    public InstructionEvaluatorLRem()
    {
        super(EOpcodes.LREM);
    }

    @Override
    protected JALParser.@NotNull JvmInsLremContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLrem();
    }
}
