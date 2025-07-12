package tokyo.peya.javasm.langjal.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.exceptions.IllegalInstructionException;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorIStoreN extends AbstractInstructionEvaluator<JALParser.JvmInsIstoreNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsIstoreNContext ctxt)
    {
        JALParser.LocalInstigationContext ins = ctxt.localInstigation();
        if (has(ctxt.INSN_ISTORE_0()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.ISTORE, 0, compiler, "I", ins);
        else if (has(ctxt.INSN_ISTORE_1()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.ISTORE, 1, compiler, "I", ins);
        else if (has(ctxt.INSN_ISTORE_2()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.ISTORE, 2, compiler, "I", ins);
        else if (has(ctxt.INSN_ISTORE_3()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.ISTORE, 3, compiler, "I", ins);

        throw new IllegalInstructionException("Unexpected instruction: " + ctxt.getText(), ctxt);
    }

    @Override
    protected JALParser.JvmInsIstoreNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIstoreN();
    }
}
