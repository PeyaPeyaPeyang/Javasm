package tokyo.peya.javasm.langjal.compiler.instructions.cast;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorI2C extends AbstractSingleInstructionEvaluator<JALParser.JvmInsI2CContext>
{
    public InstructionEvaluatorI2C()
    {
        super(EOpcodes.I2C);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popPrimitive(StackElementType.INTEGER)
                                  .pushPrimitive(StackElementType.INTEGER)  // 型変換後も整数型なので、StackElementType.INTEGERを使用
                                  .build();
    }

    @Override
    protected JALParser.JvmInsI2CContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsI2C();
    }
}
