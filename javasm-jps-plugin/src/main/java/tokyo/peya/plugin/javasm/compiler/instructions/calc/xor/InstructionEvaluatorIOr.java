package tokyo.peya.plugin.javasm.compiler.instructions.calc.xor;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIOr extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIorContext>
{
    public InstructionEvaluatorIOr()
    {
        super(EOpcodes.IOR);
    }

    @Override
    protected JALParser.JvmInsIorContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIor();
    }
}
