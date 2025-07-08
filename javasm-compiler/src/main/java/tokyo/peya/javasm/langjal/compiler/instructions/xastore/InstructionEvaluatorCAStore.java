package tokyo.peya.javasm.langjal.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorCAStore extends AbstractSingleInstructionEvaluator<JALParser.JvmInsCastoreContext>
{
    public InstructionEvaluatorCAStore()
    {
        super(EOpcodes.BALOAD);
    }

    @Override
    protected JALParser.JvmInsCastoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsCastore();
    }
}
