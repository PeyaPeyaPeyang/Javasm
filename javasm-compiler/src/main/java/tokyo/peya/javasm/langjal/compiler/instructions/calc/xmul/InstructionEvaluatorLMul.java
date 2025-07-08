package tokyo.peya.javasm.langjal.compiler.instructions.calc.xmul;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLMul extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLmulContext>
{
    public InstructionEvaluatorLMul()
    {
        super(EOpcodes.LMUL);
    }

    @Override
    protected JALParser.JvmInsLmulContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLmul();
    }
}
