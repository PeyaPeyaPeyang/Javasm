package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorD2F extends AbstractSingleInstructionEvaluator<JALParser.JvmInsD2FContext>
{
    public InstructionEvaluatorD2F()
    {
        super(EOpcodes.D2F);
    }

    @Override
    protected JALParser.JvmInsD2FContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsD2F();
    }
}
