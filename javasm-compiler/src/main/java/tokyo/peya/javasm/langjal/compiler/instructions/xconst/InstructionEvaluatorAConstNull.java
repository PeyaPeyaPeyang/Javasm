package tokyo.peya.javasm.langjal.compiler.instructions.xconst;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
