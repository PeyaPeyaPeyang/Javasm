package tokyo.peya.javasm.langjal.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorFStoreN extends AbstractInstructionEvaluator<JALParser.JvmInsFstoreNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsFstoreNContext ctxt)
    {
        if (has(ctxt.INSN_FSTORE_0()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.FSTORE, 0, evaluator, "F");
        else if (has(ctxt.INSN_FSTORE_1()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.FSTORE, 1, evaluator, "F");
        else if (has(ctxt.INSN_FSTORE_2()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.FSTORE, 2, evaluator, "F");
        else if (has(ctxt.INSN_FSTORE_3()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.FSTORE, 3, evaluator, "F");

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.JvmInsFstoreNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFstoreN();
    }
}
