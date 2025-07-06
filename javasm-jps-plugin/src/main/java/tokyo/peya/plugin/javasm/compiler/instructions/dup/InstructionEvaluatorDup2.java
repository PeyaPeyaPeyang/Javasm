package tokyo.peya.plugin.javasm.compiler.instructions.dup;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

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
