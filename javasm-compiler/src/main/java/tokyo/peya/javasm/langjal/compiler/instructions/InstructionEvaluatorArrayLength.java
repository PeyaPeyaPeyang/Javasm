package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorArrayLength
        extends AbstractSingleInstructionEvaluator<JALParser.JvmInsArraylengthContext>
{
    public InstructionEvaluatorArrayLength()
    {
        super(EOpcodes.ARRAYLENGTH);
    }

    @Override
    protected JALParser.JvmInsArraylengthContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsArraylength();
    }
}
