package tokyo.peya.javasm.langjal.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

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
