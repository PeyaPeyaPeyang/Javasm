package tokyo.peya.plugin.javasm.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorF2I extends AbstractSingleInstructionEvaluator<JALParser.JvmInsF2IContext>
{
    public InstructionEvaluatorF2I()
    {
        super(EOpcodes.F2I);
    }

    @Override
    protected JALParser.JvmInsF2IContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsF2I();
    }
}
