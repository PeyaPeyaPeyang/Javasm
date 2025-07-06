package tokyo.peya.plugin.javasm.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorSALoad extends AbstractSingleInstructionEvaluator<JALParser.JvmInsSaloadContext>
{
    public InstructionEvaluatorSALoad()
    {
        super(EOpcodes.SALOAD);
    }

    @Override
    protected JALParser.@NotNull JvmInsSaloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsSaload();
    }
}
