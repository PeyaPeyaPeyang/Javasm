package tokyo.peya.javasm.langjal.compiler.instructions.calc.xdiv;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorFDiv extends AbstractSingleInstructionEvaluator<JALParser.JvmInsFdivContext>
{
    public InstructionEvaluatorFDiv()
    {
        super(EOpcodes.FDIV);
    }

    @Override
    protected JALParser.JvmInsFdivContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFdiv();
    }
}
