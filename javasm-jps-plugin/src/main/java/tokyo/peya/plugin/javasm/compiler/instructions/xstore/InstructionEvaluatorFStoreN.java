package tokyo.peya.plugin.javasm.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorFStoreN extends AbstractInstructionEvaluator<JALParser.JvmInsFstoreNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator, JALParser.@NotNull JvmInsFstoreNContext ctxt)
    {
        if (has(ctxt.INSN_FSTORE_0()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.FSTORE_0, 0, evaluator, "F");
        else if (has(ctxt.INSN_FSTORE_1()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.FSTORE_1, 1, evaluator, "F");
        else if (has(ctxt.INSN_FSTORE_2()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.FSTORE_2, 2, evaluator, "F");
        else if (has(ctxt.INSN_FSTORE_3()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.FSTORE_3, 3, evaluator, "F");

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.JvmInsFstoreNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFstoreN();
    }
}
