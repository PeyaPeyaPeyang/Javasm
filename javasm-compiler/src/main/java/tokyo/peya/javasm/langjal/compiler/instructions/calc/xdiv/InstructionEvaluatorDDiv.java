package tokyo.peya.javasm.langjal.compiler.instructions.calc.xdiv;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
