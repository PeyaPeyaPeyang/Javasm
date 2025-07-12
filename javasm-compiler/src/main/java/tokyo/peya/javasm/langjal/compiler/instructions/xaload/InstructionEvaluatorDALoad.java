package tokyo.peya.javasm.langjal.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorDALoad extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDaloadContext>
{
    public InstructionEvaluatorDALoad()
    {
        super(EOpcodes.DALOAD);
    }

    @Override
    protected JALParser.JvmInsDaloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDaload();
    }
}
