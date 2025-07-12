package tokyo.peya.javasm.langjal.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
