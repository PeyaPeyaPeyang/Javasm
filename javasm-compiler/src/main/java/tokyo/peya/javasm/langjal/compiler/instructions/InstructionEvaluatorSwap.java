package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorSwap extends AbstractSingleInstructionEvaluator<JALParser.JvmInsSwapContext>
{
    public InstructionEvaluatorSwap()
    {
        super(EOpcodes.SWAP);
    }

    @Override
    protected JALParser.JvmInsSwapContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsSwap();
    }
}
