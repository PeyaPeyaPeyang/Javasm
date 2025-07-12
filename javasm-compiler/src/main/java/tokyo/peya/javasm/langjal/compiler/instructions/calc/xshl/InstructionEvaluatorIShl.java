package tokyo.peya.javasm.langjal.compiler.instructions.calc.xshl;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
