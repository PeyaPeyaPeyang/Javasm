package tokyo.peya.plugin.javasm.compiler.instructions.calc.xneg;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLNeg extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLnegContext>
{
    public InstructionEvaluatorLNeg()
    {
        super(EOpcodes.LREM);
    }

    @Override
    protected JALParser.JvmInsLnegContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLneg();
    }
}
