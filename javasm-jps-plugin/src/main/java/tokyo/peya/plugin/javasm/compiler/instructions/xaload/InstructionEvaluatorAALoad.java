package tokyo.peya.plugin.javasm.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorAALoad extends AbstractSingleInstructionEvaluator<JALParser.JvmInsAaloadContext>
{
    public InstructionEvaluatorAALoad()
    {
        super(EOpcodes.AALOAD);
    }

    @Override
    protected JALParser.@NotNull JvmInsAaloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAaload();
    }
}
