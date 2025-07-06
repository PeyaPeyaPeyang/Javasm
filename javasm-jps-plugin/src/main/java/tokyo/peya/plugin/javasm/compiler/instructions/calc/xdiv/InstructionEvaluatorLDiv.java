package tokyo.peya.plugin.javasm.compiler.instructions.calc.xdiv;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLDiv extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLdivContext>
{
    public InstructionEvaluatorLDiv()
    {
        super(EOpcodes.LDIV);
    }

    @Override
    protected JALParser.JvmInsLdivContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLdiv();
    }
}
