package tokyo.peya.plugin.javasm.compiler.instructions.dup;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDup2X2 extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDup2X2Context>
{
    public InstructionEvaluatorDup2X2()
    {
        super(EOpcodes.DUP2_X2);
    }

    @Override
    protected JALParser.@NotNull JvmInsDup2X2Context map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDup2X2();
    }
}
