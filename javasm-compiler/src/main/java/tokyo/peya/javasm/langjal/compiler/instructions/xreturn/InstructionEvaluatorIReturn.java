package tokyo.peya.javasm.langjal.compiler.instructions.xreturn;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIReturn extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIreturnContext>
{
    public InstructionEvaluatorIReturn()
    {
        super(EOpcodes.IRETURN);
    }

    @Override
    protected JALParser.JvmInsIreturnContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIreturn();
    }
}
