package tokyo.peya.javasm.langjal.compiler.instructions.xreturn;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
