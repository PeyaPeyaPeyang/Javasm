package tokyo.peya.javasm.langjal.compiler.instructions.calc.xdiv;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
