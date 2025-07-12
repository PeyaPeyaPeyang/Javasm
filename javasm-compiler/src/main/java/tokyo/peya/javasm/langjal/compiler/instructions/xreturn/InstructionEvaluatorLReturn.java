package tokyo.peya.javasm.langjal.compiler.instructions.xreturn;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorLReturn extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLreturnContext>
{
    public InstructionEvaluatorLReturn()
    {
        super(EOpcodes.LRETURN);
    }

    @Override
    protected JALParser.JvmInsLreturnContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLreturn();
    }
}
