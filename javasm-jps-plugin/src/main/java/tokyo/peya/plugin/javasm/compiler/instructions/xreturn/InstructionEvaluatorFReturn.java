package tokyo.peya.plugin.javasm.compiler.instructions.xreturn;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorFReturn extends AbstractSingleInstructionEvaluator<JALParser.JvmInsFreturnContext>
{
    public InstructionEvaluatorFReturn()
    {
        super(EOpcodes.FRETURN);
    }

    @Override
    protected JALParser.JvmInsFreturnContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFreturn();
    }
}
