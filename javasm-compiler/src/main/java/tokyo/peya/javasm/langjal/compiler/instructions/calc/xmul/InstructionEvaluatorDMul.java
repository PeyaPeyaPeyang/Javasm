package tokyo.peya.javasm.langjal.compiler.instructions.calc.xmul;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDMul extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDmulContext>
{
    public InstructionEvaluatorDMul()
    {
        super(EOpcodes.DMUL);
    }

    @Override
    protected JALParser.JvmInsDmulContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDmul();
    }
}
