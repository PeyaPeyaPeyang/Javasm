package tokyo.peya.plugin.javasm.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatingContext;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDStoreN extends AbstractInstructionEvaluator<JALParser.JvmInsDstoreNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull EvaluatingContext evalContxt, JALParser.@NotNull JvmInsDstoreNContext ctxt)
    {
        if (has(ctxt.INSN_DSTORE_0()))
            return visitSingle(EOpcodes.DSTORE_0);
        else if (has(ctxt.INSN_DSTORE_1()))
            return visitSingle(EOpcodes.DSTORE_1);
        else if (has(ctxt.INSN_DSTORE_2()))
            return visitSingle(EOpcodes.DSTORE_2);
        else if (has(ctxt.INSN_DSTORE_3()))
            return visitSingle(EOpcodes.DSTORE_0);

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.@NotNull JvmInsDstoreNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDstoreN();
    }
}
