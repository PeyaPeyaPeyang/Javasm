package tokyo.peya.javasm.langjal.compiler.instructions.calc.xsub;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

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
