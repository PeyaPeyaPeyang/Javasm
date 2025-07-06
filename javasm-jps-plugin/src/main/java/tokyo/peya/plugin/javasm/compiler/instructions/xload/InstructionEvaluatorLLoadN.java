package tokyo.peya.plugin.javasm.compiler.instructions.xload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLLoadN extends AbstractInstructionEvaluator<JALParser.JvmInsLloadNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsLloadNContext ctxt)
    {
        if (has(ctxt.INSN_LLOAD_0()))
            return InstructionEvaluateHelperXLoad.evaluateN(evaluator, EOpcodes.LLOAD_0, 0);
        else if (has(ctxt.INSN_LLOAD_1()))
            return InstructionEvaluateHelperXLoad.evaluateN(evaluator, EOpcodes.LLOAD_1, 1);
        else if (has(ctxt.INSN_LLOAD_2()))
            return InstructionEvaluateHelperXLoad.evaluateN(evaluator, EOpcodes.LLOAD_2, 2);
        else if (has(ctxt.INSN_LLOAD_3()))
            return InstructionEvaluateHelperXLoad.evaluateN(evaluator, EOpcodes.LLOAD_3, 3);

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.JvmInsLloadNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLloadN();
    }
}
