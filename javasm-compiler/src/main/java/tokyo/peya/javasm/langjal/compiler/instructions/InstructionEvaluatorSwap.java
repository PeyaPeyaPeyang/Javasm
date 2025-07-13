package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementCapsule;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorSwap extends AbstractSingleInstructionEvaluator<JALParser.JvmInsSwapContext>
{
    public InstructionEvaluatorSwap()
    {
        super(EOpcodes.SWAP);
    }

    @Override
    public FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        StackElementCapsule value1 = new StackElementCapsule(instruction);
        StackElementCapsule value2 = new StackElementCapsule(instruction);
        return FrameDifferenceInfo.builder(instruction)
                                  .pop(value1)
                                  .pop(value2)
                                  .push(value1)
                                  .push(value2)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsSwapContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsSwap();
    }
}
