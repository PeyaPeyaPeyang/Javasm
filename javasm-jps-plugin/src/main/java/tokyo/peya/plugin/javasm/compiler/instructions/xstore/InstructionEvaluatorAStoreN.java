package tokyo.peya.plugin.javasm.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorAStoreN extends AbstractInstructionEvaluator<JALParser.JvmInsAstoreNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator, JALParser.@NotNull JvmInsAstoreNContext ctxt)
    {
        if (has(ctxt.INSN_ASTORE_0()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.ASTORE_0, 0, evaluator, "I");
        else if (has(ctxt.INSN_ASTORE_1()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.ASTORE_1, 1, evaluator, "I");
        else if (has(ctxt.INSN_ASTORE_2()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.ASTORE_2, 2, evaluator, "I");
        else if (has(ctxt.INSN_ASTORE_3()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.ASTORE_3, 3, evaluator, "I");

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.JvmInsAstoreNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAstoreN();
    }
}
