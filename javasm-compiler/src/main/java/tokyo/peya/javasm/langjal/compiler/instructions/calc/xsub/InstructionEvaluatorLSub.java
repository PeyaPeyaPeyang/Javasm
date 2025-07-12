package tokyo.peya.javasm.langjal.compiler.instructions.calc.xsub;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
