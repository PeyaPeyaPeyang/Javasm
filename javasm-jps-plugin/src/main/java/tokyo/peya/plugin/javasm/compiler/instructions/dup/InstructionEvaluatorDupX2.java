package tokyo.peya.plugin.javasm.compiler.instructions.dup;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDupX2 extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDupX2Context>
{
    public InstructionEvaluatorDupX2()
    {
        super(EOpcodes.DUP_X2);
    }

    @Override
    protected JALParser.@NotNull JvmInsDupX2Context map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDupX2();
    }
}
