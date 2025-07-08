package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorMonitorEnter
        extends AbstractSingleInstructionEvaluator<JALParser.JvmInsMonitorenterContext>
{
    public InstructionEvaluatorMonitorEnter()
    {
        super(EOpcodes.MONITORENTER);
    }

    @Override
    protected JALParser.JvmInsMonitorenterContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsMonitorenter();
    }
}
