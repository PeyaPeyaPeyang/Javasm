package tokyo.peya.javasm.langjal.compiler.instructions.calc.xshl;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIShl extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIshlContext>
{
    public InstructionEvaluatorIShl()
    {
        super(EOpcodes.ISHL);
    }

    @Override
    protected JALParser.JvmInsIshlContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIshl();
    }
}
