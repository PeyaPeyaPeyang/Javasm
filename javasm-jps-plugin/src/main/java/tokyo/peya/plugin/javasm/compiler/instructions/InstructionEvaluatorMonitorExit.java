package tokyo.peya.plugin.javasm.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorMonitorExit
        extends AbstractSingleInstructionEvaluator<JALParser.JvmInsMonitorexitContext>
{
    public InstructionEvaluatorMonitorExit()
    {
        super(EOpcodes.MONITOREXIT);
    }

    @Override
    protected JALParser.JvmInsMonitorexitContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsMonitorexit();
    }
}
