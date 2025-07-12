package tokyo.peya.javasm.langjal.compiler.instructions.calc.xsub;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorFSub extends AbstractSingleInstructionEvaluator<JALParser.JvmInsFsubContext>
{
    public InstructionEvaluatorFSub()
    {
        super(EOpcodes.FSUB);
    }

    @Override
    protected JALParser.JvmInsFsubContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFsub();
    }
}
