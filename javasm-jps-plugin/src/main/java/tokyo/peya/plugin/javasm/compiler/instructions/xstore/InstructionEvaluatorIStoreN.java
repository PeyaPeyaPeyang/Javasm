package tokyo.peya.plugin.javasm.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIStoreN extends AbstractInstructionEvaluator<JALParser.JvmInsIstoreNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator, JALParser.@NotNull JvmInsIstoreNContext ctxt)
    {
        if (has(ctxt.INSN_ISTORE_0()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.ISTORE_0, 0, evaluator, "I");
        else if (has(ctxt.INSN_ISTORE_1()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.ISTORE_1, 1, evaluator, "I");
        else if (has(ctxt.INSN_ISTORE_2()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.ISTORE_2, 2, evaluator, "I");
        else if (has(ctxt.INSN_ISTORE_3()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.ISTORE_3, 3, evaluator, "I");

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.@NotNull JvmInsIstoreNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIstoreN();
    }
}
