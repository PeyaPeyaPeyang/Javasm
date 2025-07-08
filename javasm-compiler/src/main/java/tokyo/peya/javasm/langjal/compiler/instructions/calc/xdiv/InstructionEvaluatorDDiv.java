package tokyo.peya.javasm.langjal.compiler.instructions.calc.xdiv;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDDiv extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDdivContext>
{
    public InstructionEvaluatorDDiv()
    {
        super(EOpcodes.DDIV);
    }

    @Override
    protected JALParser.JvmInsDdivContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDdiv();
    }
}
