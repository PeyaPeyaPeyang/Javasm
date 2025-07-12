package tokyo.peya.javasm.langjal.compiler.instructions.xreturn;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
