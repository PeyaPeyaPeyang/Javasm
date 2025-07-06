package tokyo.peya.plugin.javasm.compiler.instructions.dup;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

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
