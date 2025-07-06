package tokyo.peya.plugin.javasm.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

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
