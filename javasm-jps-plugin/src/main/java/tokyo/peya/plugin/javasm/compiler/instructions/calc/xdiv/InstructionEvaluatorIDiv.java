package tokyo.peya.plugin.javasm.compiler.instructions.calc.xdiv;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIDiv extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIdivContext>
{
    public InstructionEvaluatorIDiv()
    {
        super(EOpcodes.IDIV);
    }

    @Override
    protected JALParser.@NotNull JvmInsIdivContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIdiv();
    }
}
