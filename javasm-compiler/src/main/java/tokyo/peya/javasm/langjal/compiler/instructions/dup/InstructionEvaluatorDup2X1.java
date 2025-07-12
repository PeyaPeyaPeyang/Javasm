package tokyo.peya.javasm.langjal.compiler.instructions.dup;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorDup2X1 extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDup2X1Context>
{
    public InstructionEvaluatorDup2X1()
    {
        super(EOpcodes.DUP2_X1);
    }

    @Override
    protected JALParser.JvmInsDup2X1Context map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDup2X1();
    }
}
