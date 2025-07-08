package tokyo.peya.javasm.langjal.compiler.instructions.xreturn;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

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
