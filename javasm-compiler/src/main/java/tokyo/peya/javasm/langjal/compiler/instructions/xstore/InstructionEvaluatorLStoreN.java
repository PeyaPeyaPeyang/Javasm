package tokyo.peya.javasm.langjal.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorLStoreN extends AbstractInstructionEvaluator<JALParser.JvmInsLstoreNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsLstoreNContext ctxt)
    {

        JALParser.LocalInstigationContext ins = ctxt.localInstigation();
        if (has(ctxt.INSN_LSTORE_0()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.LSTORE, 0, compiler, "L", ins);
        else if (has(ctxt.INSN_LSTORE_1()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.LSTORE, 1, compiler, "L", ins);
        else if (has(ctxt.INSN_LSTORE_2()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.LSTORE, 2, compiler, "L", ins);
        else if (has(ctxt.INSN_LSTORE_3()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.LSTORE, 3, compiler, "L", ins);

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.JvmInsLstoreNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLstoreN();
    }
}
