package tokyo.peya.javasm.langjal.compiler.instructions.calc.xdiv;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorIDiv extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIdivContext>
{
    public InstructionEvaluatorIDiv()
    {
        super(EOpcodes.IDIV);
    }

    @Override
    protected JALParser.JvmInsIdivContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIdiv();
    }
}
