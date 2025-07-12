package tokyo.peya.javasm.langjal.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorSALoad extends AbstractSingleInstructionEvaluator<JALParser.JvmInsSaloadContext>
{
    public InstructionEvaluatorSALoad()
    {
        super(EOpcodes.SALOAD);
    }

    @Override
    protected JALParser.JvmInsSaloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsSaload();
    }
}
