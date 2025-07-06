package tokyo.peya.plugin.javasm.compiler.instructions.calc.xxor;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLXOr extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLxorContext>
{
    public InstructionEvaluatorLXOr()
    {
        super(EOpcodes.LXOR);
    }

    @Override
    protected JALParser.JvmInsLxorContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLxor();
    }
}
