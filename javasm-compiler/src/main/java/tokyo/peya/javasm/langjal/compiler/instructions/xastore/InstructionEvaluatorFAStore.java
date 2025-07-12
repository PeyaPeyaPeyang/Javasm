package tokyo.peya.javasm.langjal.compiler.instructions.xastore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorFAStore extends AbstractSingleInstructionEvaluator<JALParser.JvmInsFastoreContext>
{
    public InstructionEvaluatorFAStore()
    {
        super(EOpcodes.FASTORE);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popObjectRef(TypeDescriptor.parse("[F"))
                                  .popPrimitive(StackElementType.INTEGER)
                                  .popPrimitive(StackElementType.FLOAT)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsFastoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFastore();
    }
}
