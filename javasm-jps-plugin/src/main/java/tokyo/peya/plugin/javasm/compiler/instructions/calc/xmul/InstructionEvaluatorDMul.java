package tokyo.peya.plugin.javasm.compiler.instructions.calc.xmul;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

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
