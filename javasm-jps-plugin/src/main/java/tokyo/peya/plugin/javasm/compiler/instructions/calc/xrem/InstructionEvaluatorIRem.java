package tokyo.peya.plugin.javasm.compiler.instructions.calc.xrem;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIRem extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIremContext>
{
    public InstructionEvaluatorIRem()
    {
        super(EOpcodes.IREM);
    }

    @Override
    protected JALParser.JvmInsIremContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIrem();
    }
}
