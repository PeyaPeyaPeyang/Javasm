package tokyo.peya.plugin.javasm.compiler.instructions.calc.xsub;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLSub extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLsubContext>
{
    public InstructionEvaluatorLSub()
    {
        super(EOpcodes.LSUB);
    }

    @Override
    protected JALParser.@NotNull JvmInsLsubContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLsub();
    }
}
