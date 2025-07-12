package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

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
