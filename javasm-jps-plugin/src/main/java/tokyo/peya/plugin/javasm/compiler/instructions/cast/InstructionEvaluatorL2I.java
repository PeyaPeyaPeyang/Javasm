package tokyo.peya.plugin.javasm.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorL2I extends AbstractSingleInstructionEvaluator<JALParser.JvmInsL2IContext>
{
    public InstructionEvaluatorL2I()
    {
        super(EOpcodes.L2I);
    }

    @Override
    protected JALParser.@NotNull JvmInsL2IContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsL2I();
    }
}
