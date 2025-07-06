package tokyo.peya.plugin.javasm.compiler.instructions.calc.xneg;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

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
