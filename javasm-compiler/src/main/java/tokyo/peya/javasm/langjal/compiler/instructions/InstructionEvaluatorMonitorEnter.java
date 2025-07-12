package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorMonitorEnter
        extends AbstractSingleInstructionEvaluator<JALParser.JvmInsMonitorenterContext>
{
    public InstructionEvaluatorMonitorEnter()
    {
        super(EOpcodes.MONITORENTER);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popObjectRef()
                                  .build();
    }

    @Override
    protected JALParser.JvmInsMonitorenterContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsMonitorenter();
    }
}
