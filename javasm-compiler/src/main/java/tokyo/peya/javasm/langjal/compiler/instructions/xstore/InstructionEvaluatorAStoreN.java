package tokyo.peya.javasm.langjal.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorAStoreN extends AbstractInstructionEvaluator<JALParser.JvmInsAstoreNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsAstoreNContext ctxt)
    {
        JALParser.LocalInstigationContext ins = ctxt.localInstigation();
        if (has(ctxt.INSN_ASTORE_0()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.ASTORE, 0, evaluator, "I", ins);
        else if (has(ctxt.INSN_ASTORE_1()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.ASTORE, 1, evaluator, "I", ins);
        else if (has(ctxt.INSN_ASTORE_2()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.ASTORE, 2, evaluator, "I", ins);
        else if (has(ctxt.INSN_ASTORE_3()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.ASTORE, 3, evaluator, "I", ins);

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.JvmInsAstoreNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAstoreN();
    }
}
