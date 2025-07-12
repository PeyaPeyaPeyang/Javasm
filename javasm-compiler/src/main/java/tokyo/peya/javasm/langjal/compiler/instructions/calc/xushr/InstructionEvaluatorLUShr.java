package tokyo.peya.javasm.langjal.compiler.instructions.calc.xushr;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionEvaluatorLUShr extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLushrContext>
{
    public InstructionEvaluatorLUShr()
    {
        super(EOpcodes.LUSHR);
    }

    @Override
    protected JALParser.JvmInsLushrContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLushr();
    }
}
