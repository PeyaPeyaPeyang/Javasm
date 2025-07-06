package tokyo.peya.plugin.javasm.compiler.instructions.calc.xxor;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIXOr extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIxorContext>
{
    public InstructionEvaluatorIXOr()
    {
        super(EOpcodes.IXOR);
    }

    @Override
    protected JALParser.@NotNull JvmInsIxorContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIxor();
    }
}
