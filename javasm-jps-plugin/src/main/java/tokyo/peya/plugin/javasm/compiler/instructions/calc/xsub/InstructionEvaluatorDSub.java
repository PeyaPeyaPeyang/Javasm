package tokyo.peya.plugin.javasm.compiler.instructions.calc.xsub;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDSub extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDsubContext>
{
    public InstructionEvaluatorDSub()
    {
        super(EOpcodes.DSUB);
    }

    @Override
    protected JALParser.@NotNull JvmInsDsubContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDsub();
    }
}
