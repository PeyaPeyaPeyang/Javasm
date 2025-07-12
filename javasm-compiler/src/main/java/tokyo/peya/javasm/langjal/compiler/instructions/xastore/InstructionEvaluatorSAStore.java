package tokyo.peya.javasm.langjal.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorSAStore extends AbstractSingleInstructionEvaluator<JALParser.JvmInsSastoreContext>
{
    public InstructionEvaluatorSAStore()
    {
        super(EOpcodes.SASTORE);
    }

    @Override
    protected JALParser.JvmInsSastoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsSastore();
    }
}
