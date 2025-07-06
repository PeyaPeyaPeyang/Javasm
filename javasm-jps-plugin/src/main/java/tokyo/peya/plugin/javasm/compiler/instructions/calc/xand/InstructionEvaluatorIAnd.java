package tokyo.peya.plugin.javasm.compiler.instructions.calc.xand;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIAnd extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIandContext>
{
    public InstructionEvaluatorIAnd()
    {
        super(EOpcodes.IAND);
    }

    @Override
    protected JALParser.@NotNull JvmInsIandContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIand();
    }
}
