package tokyo.peya.javasm.langjal.compiler.instructions.xreturn;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

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
