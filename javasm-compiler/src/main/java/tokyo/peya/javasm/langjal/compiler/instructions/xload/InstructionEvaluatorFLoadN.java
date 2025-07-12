package tokyo.peya.javasm.langjal.compiler.instructions.xload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.exceptions.IllegalInstructionException;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorFLoadN extends AbstractInstructionEvaluator<JALParser.JvmInsFloadNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsFloadNContext ctxt)
    {
        if (has(ctxt.INSN_FLOAD_0()))
            return InstructionEvaluateHelperXLoad.evaluateN(ctxt, compiler, EOpcodes.FLOAD, 0);
        else if (has(ctxt.INSN_FLOAD_1()))
            return InstructionEvaluateHelperXLoad.evaluateN(ctxt, compiler, EOpcodes.FLOAD, 1);
        else if (has(ctxt.INSN_FLOAD_2()))
            return InstructionEvaluateHelperXLoad.evaluateN(ctxt, compiler, EOpcodes.FLOAD, 2);
        else if (has(ctxt.INSN_FLOAD_3()))
            return InstructionEvaluateHelperXLoad.evaluateN(ctxt, compiler, EOpcodes.FLOAD, 3);

        throw new IllegalInstructionException("Unexpected instruction: " + ctxt.getText(), ctxt);
    }

    @Override
    protected JALParser.JvmInsFloadNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFloadN();
    }
}
