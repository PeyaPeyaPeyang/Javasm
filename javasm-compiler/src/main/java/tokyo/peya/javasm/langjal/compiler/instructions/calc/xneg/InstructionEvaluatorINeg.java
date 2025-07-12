package tokyo.peya.javasm.langjal.compiler.instructions.calc.xneg;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
