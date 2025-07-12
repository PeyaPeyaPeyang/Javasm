package tokyo.peya.javasm.langjal.compiler.instructions.xload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.exceptions.IllegalInstructionException;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorDLoadN extends AbstractInstructionEvaluator<JALParser.JvmInsDloadNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsDloadNContext ctxt)
    {
        if (has(ctxt.INSN_DLOAD_0()))
            return InstructionEvaluateHelperXLoad.evaluateN(this, ctxt, compiler, EOpcodes.DLOAD, 0);
        else if (has(ctxt.INSN_DLOAD_1()))
            return InstructionEvaluateHelperXLoad.evaluateN(this, ctxt, compiler, EOpcodes.DLOAD, 1);
        else if (has(ctxt.INSN_DLOAD_2()))
            return InstructionEvaluateHelperXLoad.evaluateN(this, ctxt, compiler, EOpcodes.DLOAD, 2);
        else if (has(ctxt.INSN_DLOAD_3()))
            return InstructionEvaluateHelperXLoad.evaluateN(this, ctxt, compiler, EOpcodes.DLOAD, 3);

        throw new IllegalInstructionException("Unexpected instruction: " + ctxt.getText(), ctxt);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .pushPrimitive(StackElementType.DOUBLE)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsDloadNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDloadN();
    }
}
