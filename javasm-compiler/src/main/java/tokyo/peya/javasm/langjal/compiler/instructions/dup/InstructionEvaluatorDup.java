package tokyo.peya.javasm.langjal.compiler.instructions.dup;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementCapsule;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorDup extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDupContext>
{
    public InstructionEvaluatorDup()
    {
        super(EOpcodes.DUP);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        StackElementCapsule topElement = new StackElementCapsule(instruction);

        return FrameDifferenceInfo.builder(instruction)
                                  .popToCapsule(topElement)
                                  .pushFromCapsule(topElement)
                                  .pushFromCapsule(topElement)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsDupContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDup();
    }
}
