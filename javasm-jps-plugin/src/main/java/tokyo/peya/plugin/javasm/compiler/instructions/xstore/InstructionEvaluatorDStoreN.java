package tokyo.peya.plugin.javasm.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDStoreN extends AbstractInstructionEvaluator<JALParser.JvmInsDstoreNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsDstoreNContext ctxt)
    {
        if (has(ctxt.INSN_DSTORE_0()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.DSTORE_0, 0, evaluator, "D");
        else if (has(ctxt.INSN_DSTORE_1()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.DSTORE_1, 1, evaluator, "D");
        else if (has(ctxt.INSN_DSTORE_2()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.DSTORE_2, 2, evaluator, "D");
        else if (has(ctxt.INSN_DSTORE_3()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.DSTORE_3, 3, evaluator, "D");

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.JvmInsDstoreNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDstoreN();
    }
}
