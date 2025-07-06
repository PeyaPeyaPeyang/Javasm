package tokyo.peya.plugin.javasm.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorMonitorEnter extends AbstractSingleInstructionEvaluator<JALParser.JvmInsMonitorenterContext>
{
    public InstructionEvaluatorMonitorEnter()
    {
        super(EOpcodes.MONITORENTER);
    }

    @Override
    protected JALParser.@NotNull JvmInsMonitorenterContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsMonitorenter();
    }
}
