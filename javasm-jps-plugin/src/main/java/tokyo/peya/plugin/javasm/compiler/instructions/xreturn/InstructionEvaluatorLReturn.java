package tokyo.peya.plugin.javasm.compiler.instructions.xreturn;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLReturn extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLreturnContext>
{
    public InstructionEvaluatorLReturn()
    {
        super(EOpcodes.LRETURN);
    }

    @Override
    protected JALParser.@NotNull JvmInsLreturnContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLreturn();
    }
}
