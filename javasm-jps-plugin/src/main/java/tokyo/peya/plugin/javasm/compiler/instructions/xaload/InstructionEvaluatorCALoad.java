package tokyo.peya.plugin.javasm.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorCALoad extends AbstractSingleInstructionEvaluator<JALParser.JvmInsCaloadContext>
{
    public InstructionEvaluatorCALoad()
    {
        super(EOpcodes.CALOAD);
    }

    @Override
    protected JALParser.JvmInsCaloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsCaload();
    }
}
