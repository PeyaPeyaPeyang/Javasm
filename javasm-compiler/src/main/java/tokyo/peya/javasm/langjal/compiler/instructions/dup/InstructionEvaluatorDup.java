package tokyo.peya.javasm.langjal.compiler.instructions.dup;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
