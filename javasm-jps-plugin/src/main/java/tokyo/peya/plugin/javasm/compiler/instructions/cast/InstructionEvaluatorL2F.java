package tokyo.peya.plugin.javasm.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorL2F extends AbstractSingleInstructionEvaluator<JALParser.JvmInsL2FContext>
{
    public InstructionEvaluatorL2F()
    {
        super(EOpcodes.L2F);
    }

    @Override
    protected JALParser.JvmInsL2FContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsL2F();
    }
}
