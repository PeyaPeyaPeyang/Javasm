package tokyo.peya.plugin.javasm.compiler.instructions.xload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDLoadN extends AbstractInstructionEvaluator<JALParser.JvmInsDloadNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator, JALParser.@NotNull JvmInsDloadNContext ctxt)
    {
        if (has(ctxt.INSN_DLOAD_0()))
            return InstructionEvaluateHelperXLoad.evaluateN(evaluator, EOpcodes.DLOAD_0, 0);
        else if (has(ctxt.INSN_DLOAD_1()))
            return InstructionEvaluateHelperXLoad.evaluateN(evaluator, EOpcodes.DLOAD_1, 1);
        else if (has(ctxt.INSN_DLOAD_2()))
            return InstructionEvaluateHelperXLoad.evaluateN(evaluator, EOpcodes.DLOAD_2, 2);
        else if (has(ctxt.INSN_DLOAD_3()))
            return InstructionEvaluateHelperXLoad.evaluateN(evaluator, EOpcodes.DLOAD_3, 3);

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.@NotNull JvmInsDloadNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDloadN();
    }
}
