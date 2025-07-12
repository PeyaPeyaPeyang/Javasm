package tokyo.peya.javasm.langjal.compiler.instructions.xreturn;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorIReturn extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIreturnContext>
{
    public InstructionEvaluatorIReturn()
    {
        super(EOpcodes.IRETURN);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popPrimitive(StackElementType.INTEGER)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsIreturnContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIreturn();
    }
}
