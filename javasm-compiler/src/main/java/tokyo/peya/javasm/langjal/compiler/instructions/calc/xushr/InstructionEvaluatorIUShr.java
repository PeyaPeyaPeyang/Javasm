package tokyo.peya.javasm.langjal.compiler.instructions.calc.xushr;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIUShr extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIushrContext>
{
    public InstructionEvaluatorIUShr()
    {
        super(EOpcodes.IUSHR);
    }

    @Override
    protected JALParser.JvmInsIushrContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIushr();
    }
}
