package tokyo.peya.javasm.langjal.compiler.instructions.calc.xrem;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorLRem extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLremContext>
{
    public InstructionEvaluatorLRem()
    {
        super(EOpcodes.LREM);
    }

    @Override
    protected JALParser.JvmInsLremContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLrem();
    }
}
