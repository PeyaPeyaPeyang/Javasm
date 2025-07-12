package tokyo.peya.javasm.langjal.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorDALoad extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDaloadContext>
{
    public InstructionEvaluatorDALoad()
    {
        super(EOpcodes.DALOAD);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popObjectRef(TypeDescriptor.parse("[D"))
                                  .popPrimitive(StackElementType.INTEGER)
                                  .pushPrimitive(StackElementType.DOUBLE)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsDaloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDaload();
    }
}
