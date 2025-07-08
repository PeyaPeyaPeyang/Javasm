package tokyo.peya.javasm.langjal.compiler.instructions.calc.xneg;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorINeg extends AbstractSingleInstructionEvaluator<JALParser.JvmInsInegContext>
{
    public InstructionEvaluatorINeg()
    {
        super(EOpcodes.IREM);
    }

    @Override
    protected JALParser.JvmInsInegContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIneg();
    }
}
