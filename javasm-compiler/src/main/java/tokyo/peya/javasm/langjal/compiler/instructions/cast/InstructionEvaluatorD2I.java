package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorD2I extends AbstractSingleInstructionEvaluator<JALParser.JvmInsD2IContext>
{
    public InstructionEvaluatorD2I()
    {
        super(EOpcodes.D2I);
    }

    @Override
    protected JALParser.JvmInsD2IContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsD2I();
    }
}
