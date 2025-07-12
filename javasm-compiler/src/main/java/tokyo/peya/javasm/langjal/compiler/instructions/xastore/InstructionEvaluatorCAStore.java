package tokyo.peya.javasm.langjal.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
