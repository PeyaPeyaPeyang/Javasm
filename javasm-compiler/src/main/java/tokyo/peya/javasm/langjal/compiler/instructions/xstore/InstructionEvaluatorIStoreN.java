package tokyo.peya.javasm.langjal.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIStoreN extends AbstractInstructionEvaluator<JALParser.JvmInsIstoreNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsIstoreNContext ctxt)
    {
        if (has(ctxt.INSN_ISTORE_0()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.ISTORE, 0, evaluator, "I");
        else if (has(ctxt.INSN_ISTORE_1()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.ISTORE, 1, evaluator, "I");
        else if (has(ctxt.INSN_ISTORE_2()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.ISTORE, 2, evaluator, "I");
        else if (has(ctxt.INSN_ISTORE_3()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.ISTORE, 3, evaluator, "I");

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.JvmInsIstoreNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIstoreN();
    }
}
