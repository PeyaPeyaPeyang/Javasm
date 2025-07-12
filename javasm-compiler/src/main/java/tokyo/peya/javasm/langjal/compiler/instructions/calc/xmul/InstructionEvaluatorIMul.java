package tokyo.peya.javasm.langjal.compiler.instructions.calc.xmul;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
