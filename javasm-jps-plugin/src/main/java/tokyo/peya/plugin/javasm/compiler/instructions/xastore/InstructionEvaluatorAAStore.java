package tokyo.peya.plugin.javasm.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorAAStore extends AbstractSingleInstructionEvaluator<JALParser.JvmInsAastoreContext>
{
    public InstructionEvaluatorAAStore()
    {
        super(EOpcodes.AASTORE);
    }

    @Override
    protected JALParser.@NotNull JvmInsAastoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAastore();
    }
}
