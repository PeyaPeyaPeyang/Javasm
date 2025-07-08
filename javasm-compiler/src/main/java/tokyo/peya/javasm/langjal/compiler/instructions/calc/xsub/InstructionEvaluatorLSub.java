package tokyo.peya.javasm.langjal.compiler.instructions.calc.xsub;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLSub extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLsubContext>
{
    public InstructionEvaluatorLSub()
    {
        super(EOpcodes.LSUB);
    }

    @Override
    protected JALParser.JvmInsLsubContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLsub();
    }
}
