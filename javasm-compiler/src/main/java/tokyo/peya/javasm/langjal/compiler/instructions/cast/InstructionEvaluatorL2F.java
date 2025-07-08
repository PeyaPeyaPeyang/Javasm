package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

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
