package tokyo.peya.javasm.langjal.compiler.instructions.calc.xshr;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
