package tokyo.peya.javasm.langjal.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.exceptions.IllegalInstructionException;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorDStoreN extends AbstractInstructionEvaluator<JALParser.JvmInsDstoreNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsDstoreNContext ctxt)
    {
        JALParser.LocalInstigationContext ins = ctxt.localInstigation();
        if (has(ctxt.INSN_DSTORE_0()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.DSTORE, 0, compiler, "D", ins);
        else if (has(ctxt.INSN_DSTORE_1()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.DSTORE, 1, compiler, "D", ins);
        else if (has(ctxt.INSN_DSTORE_2()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.DSTORE, 2, compiler, "D", ins);
        else if (has(ctxt.INSN_DSTORE_3()))
            return InstructionEvaluateHelperXStore.evaluateN(EOpcodes.DSTORE, 3, compiler, "D", ins);

        throw new IllegalInstructionException("Unexpected instruction: " + ctxt.getText(), ctxt);
    }

    @Override
    protected JALParser.JvmInsDstoreNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDstoreN();
    }
}
