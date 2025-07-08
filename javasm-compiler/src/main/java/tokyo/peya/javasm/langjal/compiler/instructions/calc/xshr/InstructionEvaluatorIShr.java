package tokyo.peya.javasm.langjal.compiler.instructions.calc.xshr;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

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
