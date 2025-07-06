package tokyo.peya.plugin.javasm.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorD2F extends AbstractSingleInstructionEvaluator<JALParser.JvmInsD2FContext>
{
    public InstructionEvaluatorD2F()
    {
        super(EOpcodes.D2F);
    }

    @Override
    protected JALParser.@NotNull JvmInsD2FContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsD2F();
    }
}
