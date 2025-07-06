package tokyo.peya.plugin.javasm.compiler.instructions.calc.xor;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLOr extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLorContext>
{
    public InstructionEvaluatorLOr()
    {
        super(EOpcodes.LOR);
    }

    @Override
    protected JALParser.JvmInsLorContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLor();
    }
}
