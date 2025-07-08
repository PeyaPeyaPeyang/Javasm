package tokyo.peya.javasm.langjal.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorBAStore extends AbstractSingleInstructionEvaluator<JALParser.JvmInsBastoreContext>
{
    public InstructionEvaluatorBAStore()
    {
        super(EOpcodes.BASTORE);
    }

    @Override
    protected JALParser.JvmInsBastoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsBastore();
    }
}
