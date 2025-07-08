package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorPop extends AbstractSingleInstructionEvaluator<JALParser.JvmInsPopContext>
{
    public InstructionEvaluatorPop()
    {
        super(EOpcodes.POP);
    }

    @Override
    protected JALParser.JvmInsPopContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsPop();
    }
}
