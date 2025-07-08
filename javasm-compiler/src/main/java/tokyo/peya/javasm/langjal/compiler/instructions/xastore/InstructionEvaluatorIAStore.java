package tokyo.peya.javasm.langjal.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIAStore extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIastoreContext>
{
    public InstructionEvaluatorIAStore()
    {
        super(EOpcodes.IASTORE);
    }

    @Override
    protected JALParser.JvmInsIastoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIastore();
    }
}
