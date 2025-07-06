package tokyo.peya.plugin.javasm.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLStoreN extends AbstractInstructionEvaluator<JALParser.JvmInsLstoreNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator, JALParser.@NotNull JvmInsLstoreNContext ctxt)
    {
        if (has(ctxt.INSN_LSTORE_0()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.LSTORE_0, 0, evaluator, "L");
        else if (has(ctxt.INSN_LSTORE_1()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.LSTORE_1, 1, evaluator, "L");
        else if (has(ctxt.INSN_LSTORE_2()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.LSTORE_2, 2, evaluator, "L");
        else if (has(ctxt.INSN_LSTORE_3()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.LSTORE_3, 3, evaluator, "L");

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.JvmInsLstoreNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLstoreN();
    }
}
