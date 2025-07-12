package tokyo.peya.javasm.langjal.compiler.instructions.dup;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorDup2 extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDup2Context>
{
    public InstructionEvaluatorDup2()
    {
        super(EOpcodes.DUP2);
    }

    @Override
    protected JALParser.JvmInsDup2Context map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDup2();
    }
}
