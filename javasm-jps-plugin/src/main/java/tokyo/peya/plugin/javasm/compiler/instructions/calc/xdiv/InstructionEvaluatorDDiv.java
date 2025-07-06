package tokyo.peya.plugin.javasm.compiler.instructions.calc.xdiv;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDDiv extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDdivContext>
{
    public InstructionEvaluatorDDiv()
    {
        super(EOpcodes.DDIV);
    }

    @Override
    protected JALParser.@NotNull JvmInsDdivContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDdiv();
    }
}
