package tokyo.peya.plugin.javasm.compiler.instructions.calc.xrem;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDRem extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDremContext>
{
    public InstructionEvaluatorDRem()
    {
        super(EOpcodes.DREM);
    }

    @Override
    protected JALParser.JvmInsDremContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDrem();
    }
}
