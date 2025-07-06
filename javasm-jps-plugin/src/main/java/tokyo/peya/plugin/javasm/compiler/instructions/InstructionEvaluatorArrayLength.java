package tokyo.peya.plugin.javasm.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorArrayLength extends AbstractSingleInstructionEvaluator<JALParser.JvmInsArraylengthContext>
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
