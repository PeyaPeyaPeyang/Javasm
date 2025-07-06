package tokyo.peya.plugin.javasm.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorBALoad extends AbstractSingleInstructionEvaluator<JALParser.JvmInsBaloadContext>
{
    public InstructionEvaluatorBALoad()
    {
        super(EOpcodes.BALOAD);
    }

    @Override
    protected JALParser.@NotNull JvmInsBaloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsBaload();
    }
}
