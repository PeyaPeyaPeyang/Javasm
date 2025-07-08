package tokyo.peya.javasm.langjal.compiler.instructions.calc.xshl;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLShl extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLshlContext>
{
    public InstructionEvaluatorLShl()
    {
        super(EOpcodes.LSHL);
    }

    @Override
    protected JALParser.JvmInsLshlContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLshl();
    }
}
