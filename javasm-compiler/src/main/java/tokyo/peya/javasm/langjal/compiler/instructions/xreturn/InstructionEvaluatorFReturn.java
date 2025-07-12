package tokyo.peya.javasm.langjal.compiler.instructions.xreturn;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
