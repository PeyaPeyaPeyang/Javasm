package tokyo.peya.javasm.langjal.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

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
