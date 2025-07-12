package tokyo.peya.javasm.langjal.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorBALoad extends AbstractSingleInstructionEvaluator<JALParser.JvmInsBaloadContext>
{
    public InstructionEvaluatorBALoad()
    {
        super(EOpcodes.BALOAD);
    }

    @Override
    protected JALParser.JvmInsBaloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsBaload();
    }
}
