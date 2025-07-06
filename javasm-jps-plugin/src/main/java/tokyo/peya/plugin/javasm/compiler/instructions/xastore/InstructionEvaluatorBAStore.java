package tokyo.peya.plugin.javasm.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorBAStore extends AbstractSingleInstructionEvaluator<JALParser.JvmInsBastoreContext>
{
    public InstructionEvaluatorBAStore()
    {
        super(EOpcodes.BASTORE);
    }

    @Override
    protected JALParser.@NotNull JvmInsBastoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsBastore();
    }
}
