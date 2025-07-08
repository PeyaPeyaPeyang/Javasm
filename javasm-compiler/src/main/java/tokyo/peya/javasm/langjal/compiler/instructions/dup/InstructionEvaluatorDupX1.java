package tokyo.peya.javasm.langjal.compiler.instructions.dup;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDupX1 extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDupX1Context>
{
    public InstructionEvaluatorDupX1()
    {
        super(EOpcodes.DUP_X1);
    }

    @Override
    protected JALParser.JvmInsDupX1Context map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDupX1();
    }
}
