package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorArrayLength
        extends AbstractSingleInstructionEvaluator<JALParser.JvmInsArraylengthContext>
{
    public InstructionEvaluatorArrayLength()
    {
        super(EOpcodes.ARRAYLENGTH);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popObjectRef(TypeDescriptor.parse("[Ljava/lang/Object;"))
                                  .pushPrimitive(StackElementType.INTEGER)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsArraylengthContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsArraylength();
    }
}
