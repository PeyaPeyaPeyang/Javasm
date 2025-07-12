package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorReturn extends AbstractSingleInstructionEvaluator<JALParser.JvmInsReturnContext>
{
    public InstructionEvaluatorReturn()
    {
        super(EOpcodes.RETURN);
    }

    @Override
    protected JALParser.JvmInsReturnContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsReturn();
    }
}
