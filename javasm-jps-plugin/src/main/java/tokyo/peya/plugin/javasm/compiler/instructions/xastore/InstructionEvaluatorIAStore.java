package tokyo.peya.plugin.javasm.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIAStore extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIastoreContext>
{
    public InstructionEvaluatorIAStore()
    {
        super(EOpcodes.IASTORE);
    }

    @Override
    protected JALParser.JvmInsIastoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIastore();
    }
}
