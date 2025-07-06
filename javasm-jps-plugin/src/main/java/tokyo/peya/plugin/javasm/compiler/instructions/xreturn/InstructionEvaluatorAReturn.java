package tokyo.peya.plugin.javasm.compiler.instructions.xreturn;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorAReturn extends AbstractSingleInstructionEvaluator<JALParser.JvmInsAreturnContext>
{
    public InstructionEvaluatorAReturn()
    {
        super(EOpcodes.ARETURN);
    }

    @Override
    protected JALParser.@NotNull JvmInsAreturnContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAreturn();
    }
}
