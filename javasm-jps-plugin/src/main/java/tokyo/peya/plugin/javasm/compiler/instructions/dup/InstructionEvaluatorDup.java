package tokyo.peya.plugin.javasm.compiler.instructions.dup;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDup extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDupContext>
{
    public InstructionEvaluatorDup()
    {
        super(EOpcodes.DUP);
    }

    @Override
    protected JALParser.@NotNull JvmInsDupContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDup();
    }
}
