package tokyo.peya.javasm.langjal.compiler.instructions.dup;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDup extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDupContext>
{
    public InstructionEvaluatorDup()
    {
        super(EOpcodes.DUP);
    }

    @Override
    protected JALParser.JvmInsDupContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDup();
    }
}
