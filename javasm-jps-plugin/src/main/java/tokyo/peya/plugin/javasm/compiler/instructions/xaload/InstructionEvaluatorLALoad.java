package tokyo.peya.plugin.javasm.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLALoad extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLaloadContext>
{
    public InstructionEvaluatorLALoad()
    {
        super(EOpcodes.LALOAD);
    }

    @Override
    protected JALParser.@NotNull JvmInsLaloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLaload();
    }
}
