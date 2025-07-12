package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorF2L extends AbstractSingleInstructionEvaluator<JALParser.JvmInsF2LContext>
{
    public InstructionEvaluatorF2L()
    {
        super(EOpcodes.F2L);
    }

    @Override
    protected JALParser.JvmInsF2LContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsF2L();
    }
}
