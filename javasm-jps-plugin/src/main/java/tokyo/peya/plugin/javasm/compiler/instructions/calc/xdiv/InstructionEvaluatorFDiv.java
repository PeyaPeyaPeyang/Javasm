package tokyo.peya.plugin.javasm.compiler.instructions.calc.xdiv;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorFDiv extends AbstractSingleInstructionEvaluator<JALParser.JvmInsFdivContext>
{
    public InstructionEvaluatorFDiv()
    {
        super(EOpcodes.FDIV);
    }

    @Override
    protected JALParser.@NotNull JvmInsFdivContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFdiv();
    }
}
