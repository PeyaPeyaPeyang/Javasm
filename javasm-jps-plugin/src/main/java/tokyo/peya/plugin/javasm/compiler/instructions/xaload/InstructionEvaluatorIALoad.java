package tokyo.peya.plugin.javasm.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIALoad extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIaloadContext>
{
    public InstructionEvaluatorIALoad()
    {
        super(EOpcodes.IALOAD);
    }

    @Override
    protected JALParser.@NotNull JvmInsIaloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIaload();
    }
}
