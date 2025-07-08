package tokyo.peya.javasm.langjal.compiler.instructions.dup;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDupX2 extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDupX2Context>
{
    public InstructionEvaluatorDupX2()
    {
        super(EOpcodes.DUP_X2);
    }

    @Override
    protected JALParser.JvmInsDupX2Context map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDupX2();
    }
}
