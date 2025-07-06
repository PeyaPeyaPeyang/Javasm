package tokyo.peya.plugin.javasm.compiler.instructions.xload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorILoadN extends AbstractInstructionEvaluator<JALParser.JvmInsIloadNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator, JALParser.@NotNull JvmInsIloadNContext ctxt)
    {
        if (has(ctxt.INSN_ILOAD_0()))
            return InstructionEvaluateHelperXLoad.evaluateN(evaluator, EOpcodes.ILOAD_0, 0);
        else if (has(ctxt.INSN_ILOAD_1()))
            return InstructionEvaluateHelperXLoad.evaluateN(evaluator, EOpcodes.ILOAD_1, 1);
        else if (has(ctxt.INSN_ILOAD_2()))
            return InstructionEvaluateHelperXLoad.evaluateN(evaluator, EOpcodes.ILOAD_2, 2);
        else if (has(ctxt.INSN_ILOAD_3()))
            return InstructionEvaluateHelperXLoad.evaluateN(evaluator, EOpcodes.ILOAD_3, 3);

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.@NotNull JvmInsIloadNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIloadN();
    }
}
