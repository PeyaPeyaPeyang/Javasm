package tokyo.peya.plugin.javasm.compiler.instructions.calc.xushr;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

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
