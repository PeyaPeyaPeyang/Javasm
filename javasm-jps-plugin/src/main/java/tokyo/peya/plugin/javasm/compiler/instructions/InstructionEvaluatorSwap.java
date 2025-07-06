package tokyo.peya.plugin.javasm.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorSwap extends AbstractSingleInstructionEvaluator<JALParser.JvmInsSwapContext>
{
    public InstructionEvaluatorSwap()
    {
        super(EOpcodes.SWAP);
    }

    @Override
    protected JALParser.@NotNull JvmInsSwapContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsSwap();
    }
}
