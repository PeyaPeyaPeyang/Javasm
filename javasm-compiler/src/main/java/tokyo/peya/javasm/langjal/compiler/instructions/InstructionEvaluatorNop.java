package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorNop extends AbstractSingleInstructionEvaluator<JALParser.JvmInsNopContext>
{
    public InstructionEvaluatorNop()
    {
        super(EOpcodes.NOP);
    }

    @Override
    protected JALParser.JvmInsNopContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsNop();
    }
}
