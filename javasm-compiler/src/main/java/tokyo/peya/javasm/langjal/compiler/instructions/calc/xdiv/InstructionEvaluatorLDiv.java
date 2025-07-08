package tokyo.peya.javasm.langjal.compiler.instructions.calc.xdiv;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

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
