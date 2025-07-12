package tokyo.peya.javasm.langjal.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorLALoad extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLaloadContext>
{
    public InstructionEvaluatorLALoad()
    {
        super(EOpcodes.LALOAD);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popObjectRef(TypeDescriptor.parse("[L"))
                                  .popPrimitive(StackElementType.INTEGER)
                                  .pushPrimitive(StackElementType.LONG)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsLaloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLaload();
    }
}
