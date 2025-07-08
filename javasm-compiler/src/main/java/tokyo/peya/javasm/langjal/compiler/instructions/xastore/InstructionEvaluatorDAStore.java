package tokyo.peya.javasm.langjal.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDAStore extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDastoreContext>
{
    public InstructionEvaluatorDAStore()
    {
        super(EOpcodes.DASTORE);
    }

    @Override
    protected JALParser.JvmInsDastoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDastore();
    }
}
