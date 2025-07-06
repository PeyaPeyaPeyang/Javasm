package tokyo.peya.plugin.javasm.compiler.instructions.xload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorALoadN extends AbstractInstructionEvaluator<JALParser.JvmInsAloadNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsAloadNContext ctxt)
    {
        if (has(ctxt.INSN_ALOAD_0()))
            return InstructionEvaluateHelperXLoad.evaluateN(evaluator, EOpcodes.ALOAD_0, 0);
        else if (has(ctxt.INSN_ALOAD_1()))
            return InstructionEvaluateHelperXLoad.evaluateN(evaluator, EOpcodes.ALOAD_1, 1);
        else if (has(ctxt.INSN_ALOAD_2()))
            return InstructionEvaluateHelperXLoad.evaluateN(evaluator, EOpcodes.ALOAD_2, 2);
        else if (has(ctxt.INSN_ALOAD_3()))
            return InstructionEvaluateHelperXLoad.evaluateN(evaluator, EOpcodes.ALOAD_3, 3);

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.JvmInsAloadNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAloadN();
    }
}
