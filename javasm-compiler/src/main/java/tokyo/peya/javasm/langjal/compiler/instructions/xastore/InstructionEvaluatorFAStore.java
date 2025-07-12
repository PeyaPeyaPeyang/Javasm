package tokyo.peya.javasm.langjal.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorFAStore extends AbstractSingleInstructionEvaluator<JALParser.JvmInsFastoreContext>
{
    public InstructionEvaluatorFAStore()
    {
        super(EOpcodes.FASTORE);
    }

    @Override
    protected JALParser.JvmInsFastoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFastore();
    }
}
