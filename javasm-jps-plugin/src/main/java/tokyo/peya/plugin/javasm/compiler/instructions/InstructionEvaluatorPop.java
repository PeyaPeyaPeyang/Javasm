package tokyo.peya.plugin.javasm.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

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
