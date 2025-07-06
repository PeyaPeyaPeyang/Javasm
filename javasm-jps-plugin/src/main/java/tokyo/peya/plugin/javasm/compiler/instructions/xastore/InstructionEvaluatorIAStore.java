package tokyo.peya.plugin.javasm.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIAStore extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLastoreContext>
{
    public InstructionEvaluatorIAStore()
    {
        super(EOpcodes.LASTORE);
    }

    @Override
    protected JALParser.@NotNull JvmInsLastoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLastore();
    }
}
