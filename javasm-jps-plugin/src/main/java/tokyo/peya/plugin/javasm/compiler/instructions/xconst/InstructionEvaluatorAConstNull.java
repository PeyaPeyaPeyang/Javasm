package tokyo.peya.plugin.javasm.compiler.instructions.xconst;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorAConstNull
        extends AbstractSingleInstructionEvaluator<JALParser.JvmInsAconstNullContext>
{
    public InstructionEvaluatorAConstNull()
    {
        super(EOpcodes.ACONST_NULL);
    }

    @Override
    protected JALParser.JvmInsAconstNullContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAconstNull();
    }
}
