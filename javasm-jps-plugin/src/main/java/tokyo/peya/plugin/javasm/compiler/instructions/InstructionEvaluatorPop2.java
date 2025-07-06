package tokyo.peya.plugin.javasm.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorPop2 extends AbstractSingleInstructionEvaluator<JALParser.JvmInsPop2Context>
{
    public InstructionEvaluatorPop2()
    {
        super(EOpcodes.POP2);
    }

    @Override
    protected JALParser.JvmInsPop2Context map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsPop2();
    }
}
