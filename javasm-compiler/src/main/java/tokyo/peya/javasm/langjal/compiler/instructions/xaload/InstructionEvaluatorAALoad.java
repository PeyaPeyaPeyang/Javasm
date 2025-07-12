package tokyo.peya.javasm.langjal.compiler.instructions.xaload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorAALoad extends AbstractSingleInstructionEvaluator<JALParser.JvmInsAaloadContext>
{
    public InstructionEvaluatorAALoad()
    {
        super(EOpcodes.AALOAD);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popObjectRef(TypeDescriptor.parse("[Ljava/lang/Object;"))
                                  .popPrimitive(StackElementType.INTEGER)
                                  .pushObjectRef()
                                  .build();
    }

    @Override
    protected JALParser.JvmInsAaloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAaload();
    }
}
