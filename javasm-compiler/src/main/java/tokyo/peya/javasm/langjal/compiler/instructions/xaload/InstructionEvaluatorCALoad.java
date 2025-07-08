package tokyo.peya.javasm.langjal.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

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
