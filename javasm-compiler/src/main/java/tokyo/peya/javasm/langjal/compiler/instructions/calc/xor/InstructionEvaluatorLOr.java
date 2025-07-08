package tokyo.peya.javasm.langjal.compiler.instructions.calc.xor;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

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
