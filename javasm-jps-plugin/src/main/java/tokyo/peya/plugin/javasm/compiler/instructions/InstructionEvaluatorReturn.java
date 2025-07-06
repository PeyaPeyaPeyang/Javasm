package tokyo.peya.plugin.javasm.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorReturn extends AbstractSingleInstructionEvaluator<JALParser.JvmInsReturnContext>
{
    public InstructionEvaluatorReturn()
    {
        super(EOpcodes.RETURN);
    }

    @Override
    protected JALParser.@NotNull JvmInsReturnContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsReturn();
    }
}
