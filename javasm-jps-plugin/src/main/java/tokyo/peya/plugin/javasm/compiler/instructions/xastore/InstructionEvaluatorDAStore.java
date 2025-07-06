package tokyo.peya.plugin.javasm.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDAStore extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDastoreContext>
{
    public InstructionEvaluatorDAStore()
    {
        super(EOpcodes.DASTORE);
    }

    @Override
    protected JALParser.@NotNull JvmInsDastoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDastore();
    }
}
