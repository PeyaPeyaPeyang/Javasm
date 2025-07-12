package tokyo.peya.javasm.langjal.compiler.instructions.calc.xmul;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
