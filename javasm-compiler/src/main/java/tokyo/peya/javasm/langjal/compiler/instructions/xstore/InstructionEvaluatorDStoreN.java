package tokyo.peya.javasm.langjal.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDStoreN extends AbstractInstructionEvaluator<JALParser.JvmInsDstoreNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsDstoreNContext ctxt)
    {
        JALParser.LocalInstigationContext ins = ctxt.localInstigation();
        if (has(ctxt.INSN_DSTORE_0()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.DSTORE, 0, evaluator, "D", ins);
        else if (has(ctxt.INSN_DSTORE_1()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.DSTORE, 1, evaluator, "D", ins);
        else if (has(ctxt.INSN_DSTORE_2()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.DSTORE, 2, evaluator, "D", ins);
        else if (has(ctxt.INSN_DSTORE_3()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.DSTORE, 3, evaluator, "D", ins);

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.JvmInsDstoreNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDstoreN();
    }
}
