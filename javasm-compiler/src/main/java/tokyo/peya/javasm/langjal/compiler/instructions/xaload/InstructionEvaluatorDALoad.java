package tokyo.peya.javasm.langjal.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

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
