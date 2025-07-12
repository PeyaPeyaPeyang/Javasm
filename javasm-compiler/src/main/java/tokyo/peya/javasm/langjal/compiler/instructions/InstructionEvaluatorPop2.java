package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
