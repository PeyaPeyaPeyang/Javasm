package tokyo.peya.javasm.langjal.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
