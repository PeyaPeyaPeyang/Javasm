package tokyo.peya.javasm.langjal.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorIAStore extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIastoreContext>
{
    public InstructionEvaluatorIAStore()
    {
        super(EOpcodes.IASTORE);
    }

    @Override
    public FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popPrimitive(StackElementType.INTEGER)
                                  .popPrimitive(StackElementType.INTEGER)
                                  .popObjectRef(TypeDescriptor.parse("[I"))
                                  .build();
    }

    @Override
    protected JALParser.JvmInsIastoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIastore();
    }
}
