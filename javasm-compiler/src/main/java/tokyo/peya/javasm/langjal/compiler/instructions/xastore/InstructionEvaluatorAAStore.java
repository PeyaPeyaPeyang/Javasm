package tokyo.peya.javasm.langjal.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorAAStore extends AbstractSingleInstructionEvaluator<JALParser.JvmInsAastoreContext>
{
    public InstructionEvaluatorAAStore()
    {
        super(EOpcodes.AASTORE);
    }

    @Override
    protected JALParser.JvmInsAastoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAastore();
    }
}
