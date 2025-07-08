package tokyo.peya.javasm.langjal.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorFALoad extends AbstractSingleInstructionEvaluator<JALParser.JvmInsFaloadContext>
{
    public InstructionEvaluatorFALoad()
    {
        super(EOpcodes.FALOAD);
    }

    @Override
    protected JALParser.JvmInsFaloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFaload();
    }
}
