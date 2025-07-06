package tokyo.peya.plugin.javasm.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorFALoad extends AbstractSingleInstructionEvaluator<JALParser.JvmInsFaloadContext>
{
    public InstructionEvaluatorFALoad()
    {
        super(EOpcodes.FALOAD);
    }

    @Override
    protected JALParser.@NotNull JvmInsFaloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFaload();
    }
}
