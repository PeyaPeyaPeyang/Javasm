package tokyo.peya.javasm.langjal.compiler.instructions.calc.xneg;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorDNeg extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDnegContext>
{
    public InstructionEvaluatorDNeg()
    {
        super(EOpcodes.DREM);
    }

    @Override
    protected JALParser.JvmInsDnegContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDneg();
    }
}
