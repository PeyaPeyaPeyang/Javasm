package tokyo.peya.javasm.langjal.compiler.instructions.calc;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorLCmp extends AbstractSingleInstructionEvaluator<JALParser.JvmInsLcmpContext>
{
    public InstructionEvaluatorLCmp()
    {
        super(EOpcodes.LCMP);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popPrimitive(StackElementType.LONG)
                                  .popPrimitive(StackElementType.LONG)
                                  .pushPrimitive(StackElementType.LONG)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsLcmpContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLcmp();
    }
}
