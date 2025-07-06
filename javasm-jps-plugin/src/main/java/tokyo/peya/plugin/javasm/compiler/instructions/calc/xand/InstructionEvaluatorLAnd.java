package tokyo.peya.plugin.javasm.compiler.instructions.calc.xand;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLAnd extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLandContext>
{
    public InstructionEvaluatorLAnd()
    {
        super(EOpcodes.LAND);
    }

    @Override
    protected JALParser.@NotNull JvmInsLandContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLand();
    }
}
