package tokyo.peya.javasm.langjal.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorSALoad extends AbstractSingleInstructionEvaluator<JALParser.JvmInsSaloadContext>
{
    public InstructionEvaluatorSALoad()
    {
        super(EOpcodes.SALOAD);
    }

    @Override
    public FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popPrimitive(StackElementType.INTEGER)
                                  .popObjectRef(TypeDescriptor.parse("[S"))
                                  .pushPrimitive(StackElementType.INTEGER)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsSaloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsSaload();
    }
}
