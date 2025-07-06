package tokyo.peya.plugin.javasm.compiler.instructions.calc.xshl;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLShl extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLshlContext>
{
    public InstructionEvaluatorLShl()
    {
        super(EOpcodes.LSHL);
    }

    @Override
    protected JALParser.@NotNull JvmInsLshlContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLshl();
    }
}
