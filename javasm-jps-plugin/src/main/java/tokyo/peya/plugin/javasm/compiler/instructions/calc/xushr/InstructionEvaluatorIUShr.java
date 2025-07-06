package tokyo.peya.plugin.javasm.compiler.instructions.calc.xushr;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIUShr extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIushrContext>
{
    public InstructionEvaluatorIUShr()
    {
        super(EOpcodes.IUSHR);
    }

    @Override
    protected JALParser.@NotNull JvmInsIushrContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIushr();
    }
}
