package tokyo.peya.javasm.langjal.compiler.instructions.calc.xsub;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorDSub extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDsubContext>
{
    public InstructionEvaluatorDSub()
    {
        super(EOpcodes.DSUB);
    }

    @Override
    protected JALParser.JvmInsDsubContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDsub();
    }
}
