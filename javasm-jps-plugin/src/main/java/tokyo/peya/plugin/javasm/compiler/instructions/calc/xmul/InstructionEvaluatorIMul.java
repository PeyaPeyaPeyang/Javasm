package tokyo.peya.plugin.javasm.compiler.instructions.calc.xmul;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

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
