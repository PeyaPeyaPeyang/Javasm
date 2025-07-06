package tokyo.peya.plugin.javasm.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorNop extends AbstractSingleInstructionEvaluator<JALParser.JvmInsNopContext>
{
    public InstructionEvaluatorNop()
    {
        super(EOpcodes.NOP);
    }

    @Override
    protected JALParser.JvmInsNopContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsNop();
    }
}
