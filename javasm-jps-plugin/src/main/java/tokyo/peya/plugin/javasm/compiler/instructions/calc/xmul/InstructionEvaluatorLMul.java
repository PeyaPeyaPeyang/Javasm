package tokyo.peya.plugin.javasm.compiler.instructions.calc.xmul;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLMul extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLmulContext>
{
    public InstructionEvaluatorLMul()
    {
        super(EOpcodes.LMUL);
    }

    @Override
    protected JALParser.@NotNull JvmInsLmulContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLmul();
    }
}
