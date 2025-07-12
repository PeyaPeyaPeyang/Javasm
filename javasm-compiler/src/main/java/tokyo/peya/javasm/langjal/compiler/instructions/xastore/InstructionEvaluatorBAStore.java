package tokyo.peya.javasm.langjal.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorBAStore extends AbstractSingleInstructionEvaluator<JALParser.JvmInsBastoreContext>
{
    public InstructionEvaluatorBAStore()
    {
        super(EOpcodes.BASTORE);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popObjectRef(TypeDescriptor.parse("[B"))
                                  .popPrimitive(StackElementType.INTEGER)
                                  .popPrimitive(StackElementType.INTEGER)  // Byte は 整数として扱われる
                                  .build();
    }

    @Override
    protected JALParser.JvmInsBastoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsBastore();
    }
}
