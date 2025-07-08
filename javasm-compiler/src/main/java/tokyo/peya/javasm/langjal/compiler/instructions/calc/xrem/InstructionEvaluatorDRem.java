package tokyo.peya.javasm.langjal.compiler.instructions.calc.xrem;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDRem extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDremContext>
{
    public InstructionEvaluatorDRem()
    {
        super(EOpcodes.DREM);
    }

    @Override
    protected JALParser.JvmInsDremContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDrem();
    }
}
