package tokyo.peya.javasm.langjal.compiler.instructions.calc.xmul;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
