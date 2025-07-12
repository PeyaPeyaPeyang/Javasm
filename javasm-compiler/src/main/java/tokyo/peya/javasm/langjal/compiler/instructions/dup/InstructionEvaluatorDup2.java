package tokyo.peya.javasm.langjal.compiler.instructions.dup;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementCapsule;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorDup2 extends AbstractSingleInstructionEvaluator<JALParser.JvmInsDup2Context>
{
    public InstructionEvaluatorDup2()
    {
        super(EOpcodes.DUP2);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        StackElementCapsule topElement1 = new StackElementCapsule(instruction);
        StackElementCapsule topElement2 = new StackElementCapsule(instruction);
        // ..., topElement1, topElement2
        // -> ..., topElement1, topElement2, topElement1, topElement2
        return FrameDifferenceInfo.builder(instruction)
                                  .popToCapsule(topElement1)
                                  .popToCapsule(topElement2)
                                  .pushFromCapsule(topElement1)
                                  .pushFromCapsule(topElement2)
                                  .pushFromCapsule(topElement1)
                                  .pushFromCapsule(topElement2)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsDup2Context map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDup2();
    }
}
