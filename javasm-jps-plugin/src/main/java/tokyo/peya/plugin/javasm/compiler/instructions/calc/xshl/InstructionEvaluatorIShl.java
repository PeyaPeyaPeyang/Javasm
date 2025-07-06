package tokyo.peya.plugin.javasm.compiler.instructions.calc.xshl;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

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
