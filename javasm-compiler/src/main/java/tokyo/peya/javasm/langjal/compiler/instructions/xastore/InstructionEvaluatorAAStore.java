package tokyo.peya.javasm.langjal.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

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
