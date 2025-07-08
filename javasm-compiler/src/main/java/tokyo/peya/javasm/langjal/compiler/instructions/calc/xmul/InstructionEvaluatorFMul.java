package tokyo.peya.javasm.langjal.compiler.instructions.calc.xmul;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorFMul extends AbstractSingleInstructionEvaluator<JALParser.JvmInsFmulContext>
{
    public InstructionEvaluatorFMul()
    {
        super(EOpcodes.FMUL);
    }

    @Override
    protected JALParser.JvmInsFmulContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFmul();
    }
}
