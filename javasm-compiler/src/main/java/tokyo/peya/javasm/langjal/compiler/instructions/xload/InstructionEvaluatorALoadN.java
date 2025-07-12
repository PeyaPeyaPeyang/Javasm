package tokyo.peya.javasm.langjal.compiler.instructions.xload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorALoadN extends AbstractInstructionEvaluator<JALParser.JvmInsAloadNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsAloadNContext ctxt)
    {
        if (has(ctxt.INSN_ALOAD_0()))
            return InstructionEvaluateHelperXLoad.evaluateN(compiler, EOpcodes.ALOAD, 0);
        else if (has(ctxt.INSN_ALOAD_1()))
            return InstructionEvaluateHelperXLoad.evaluateN(compiler, EOpcodes.ALOAD, 1);
        else if (has(ctxt.INSN_ALOAD_2()))
            return InstructionEvaluateHelperXLoad.evaluateN(compiler, EOpcodes.ALOAD, 2);
        else if (has(ctxt.INSN_ALOAD_3()))
            return InstructionEvaluateHelperXLoad.evaluateN(compiler, EOpcodes.ALOAD, 3);

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.JvmInsAloadNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAloadN();
    }
}
