package tokyo.peya.plugin.javasm.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorCALoad extends AbstractSingleInstructionEvaluator<JALParser.JvmInsBaloadContext>
{
    public InstructionEvaluatorCALoad()
    {
        super(EOpcodes.BALOAD);
    }

    @Override
    protected JALParser.JvmInsBaloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsBaload();
    }
}
