package tokyo.peya.javasm.langjal.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorAAStore extends AbstractSingleInstructionEvaluator<JALParser.JvmInsAastoreContext>
{
    public InstructionEvaluatorAAStore()
    {
        super(EOpcodes.AASTORE);
    }

    @Override
    public FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popObjectRef(TypeDescriptor.parse("[java/lang/Object;"))
                                  .popPrimitive(StackElementType.INTEGER)
                                  .popObjectRef(TypeDescriptor.parse("Ljava/lang/Object;"))
                                  .build();
    }

    @Override
    protected JALParser.JvmInsAastoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAastore();
    }
}
