package tokyo.peya.javasm.langjal.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
