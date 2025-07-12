package tokyo.peya.javasm.langjal.compiler.instructions.calc.xrem;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorIRem extends AbstractSingleInstructionEvaluator<JALParser.JvmInsIremContext>
{
    public InstructionEvaluatorIRem()
    {
        super(EOpcodes.IREM);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popPrimitive(StackElementType.INTEGER)
                                  .popPrimitive(StackElementType.INTEGER)
                                  .pushPrimitive(StackElementType.INTEGER)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsIremContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIrem();
    }
}
