package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
