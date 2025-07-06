package tokyo.peya.plugin.javasm.compiler.instructions.calc.xmul;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorFMul extends AbstractSingleInstructionEvaluator<JALParser.JvmInsFmulContext>
{
    public InstructionEvaluatorFMul()
    {
        super(EOpcodes.FMUL);
    }

    @Override
    protected JALParser.@NotNull JvmInsFmulContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFmul();
    }
}
