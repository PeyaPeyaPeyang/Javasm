package tokyo.peya.javasm.langjal.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLStoreN extends AbstractInstructionEvaluator<JALParser.JvmInsLstoreNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsLstoreNContext ctxt)
    {

        JALParser.LocalInstigationContext ins = ctxt.localInstigation();
        if (has(ctxt.INSN_LSTORE_0()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.LSTORE, 0, evaluator, "L", ins);
        else if (has(ctxt.INSN_LSTORE_1()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.LSTORE, 1, evaluator, "L", ins);
        else if (has(ctxt.INSN_LSTORE_2()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.LSTORE, 2, evaluator, "L", ins);
        else if (has(ctxt.INSN_LSTORE_3()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.LSTORE, 3, evaluator, "L", ins);

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.JvmInsLstoreNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLstoreN();
    }
}
