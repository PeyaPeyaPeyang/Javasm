package tokyo.peya.plugin.javasm.compiler.instructions.calc.xshr;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIShr extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIshrContext>
{
    public InstructionEvaluatorIShr()
    {
        super(EOpcodes.ISHR);
    }

    @Override
    protected JALParser.JvmInsIshrContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIshr();
    }
}
