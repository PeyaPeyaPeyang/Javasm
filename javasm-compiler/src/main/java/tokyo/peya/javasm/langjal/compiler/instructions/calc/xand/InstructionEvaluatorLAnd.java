package tokyo.peya.javasm.langjal.compiler.instructions.calc.xand;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorLAnd extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLandContext>
{
    public InstructionEvaluatorLAnd()
    {
        super(EOpcodes.LAND);
    }

    @Override
    protected JALParser.JvmInsLandContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLand();
    }
}
