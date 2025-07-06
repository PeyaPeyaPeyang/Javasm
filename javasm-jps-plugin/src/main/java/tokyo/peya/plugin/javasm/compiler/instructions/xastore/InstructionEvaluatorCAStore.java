package tokyo.peya.plugin.javasm.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorCAStore extends AbstractSingleInstructionEvaluator<JALParser.JvmInsCastoreContext>
{
    public InstructionEvaluatorCAStore()
    {
        super(EOpcodes.BALOAD);
    }

    @Override
    protected JALParser.@NotNull JvmInsCastoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsCastore();
    }
}
