package tokyo.peya.javasm.langjal.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorLAStore extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLastoreContext>
{
    public InstructionEvaluatorLAStore()
    {
        super(EOpcodes.LASTORE);
    }

    @Override
    protected JALParser.JvmInsLastoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLastore();
    }
}
