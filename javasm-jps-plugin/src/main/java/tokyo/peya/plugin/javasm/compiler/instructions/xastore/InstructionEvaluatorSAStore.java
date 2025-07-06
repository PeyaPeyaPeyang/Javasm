package tokyo.peya.plugin.javasm.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

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
