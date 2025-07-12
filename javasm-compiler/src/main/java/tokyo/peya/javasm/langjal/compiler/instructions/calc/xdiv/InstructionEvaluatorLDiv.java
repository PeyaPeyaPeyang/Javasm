package tokyo.peya.javasm.langjal.compiler.instructions.calc.xdiv;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
