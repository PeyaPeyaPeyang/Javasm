package tokyo.peya.javasm.langjal.compiler.instructions.calc.xmul;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIMul extends AbstractSingleInstructionEvaluator<JALParser.JvmInsImulContext>
{
    public InstructionEvaluatorIMul()
    {
        super(EOpcodes.IMUL);
    }

    @Override
    protected JALParser.JvmInsImulContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsImul();
    }
}
