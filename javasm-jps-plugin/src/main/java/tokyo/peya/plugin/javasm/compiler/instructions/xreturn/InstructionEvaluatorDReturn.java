package tokyo.peya.plugin.javasm.compiler.instructions.xreturn;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDReturn extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDreturnContext>
{
    public InstructionEvaluatorDReturn()
    {
        super(EOpcodes.DRETURN);
    }

    @Override
    protected JALParser.JvmInsDreturnContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDreturn();
    }
}
