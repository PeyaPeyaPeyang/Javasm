package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorL2I extends AbstractSingleInstructionEvaluator<JALParser.JvmInsL2IContext>
{
    public InstructionEvaluatorL2I()
    {
        super(EOpcodes.L2I);
    }

    @Override
    protected JALParser.JvmInsL2IContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsL2I();
    }
}
