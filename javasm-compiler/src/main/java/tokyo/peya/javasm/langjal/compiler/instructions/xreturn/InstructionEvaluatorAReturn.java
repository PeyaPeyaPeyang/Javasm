package tokyo.peya.javasm.langjal.compiler.instructions.xreturn;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorAReturn extends AbstractSingleInstructionEvaluator<JALParser.JvmInsAreturnContext>
{
    public InstructionEvaluatorAReturn()
    {
        super(EOpcodes.ARETURN);
    }

    @Override
    protected JALParser.JvmInsAreturnContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAreturn();
    }
}
