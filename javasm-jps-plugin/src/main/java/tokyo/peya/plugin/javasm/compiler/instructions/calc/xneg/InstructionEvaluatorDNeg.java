package tokyo.peya.plugin.javasm.compiler.instructions.calc.xneg;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDNeg extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDnegContext>
{
    public InstructionEvaluatorDNeg()
    {
        super(EOpcodes.DREM);
    }

    @Override
    protected JALParser.@NotNull JvmInsDnegContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDneg();
    }
}
