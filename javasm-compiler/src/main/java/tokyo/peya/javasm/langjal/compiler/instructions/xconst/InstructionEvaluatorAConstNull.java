package tokyo.peya.javasm.langjal.compiler.instructions.xconst;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

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
