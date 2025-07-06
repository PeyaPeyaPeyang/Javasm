package tokyo.peya.plugin.javasm.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatingContext;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorFStoreN extends AbstractInstructionEvaluator<JALParser.JvmInsFstoreNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull EvaluatingContext evalContxt, JALParser.@NotNull JvmInsFstoreNContext ctxt)
    {
        if (has(ctxt.INSN_FSTORE_0()))
            return visitSingle(EOpcodes.FSTORE_0);
        else if (has(ctxt.INSN_FSTORE_1()))
            return visitSingle(EOpcodes.FSTORE_1);
        else if (has(ctxt.INSN_FSTORE_2()))
            return visitSingle(EOpcodes.FSTORE_2);
        else if (has(ctxt.INSN_FSTORE_3()))
            return visitSingle(EOpcodes.FSTORE_0);

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.@NotNull JvmInsFstoreNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFstoreN();
    }
}
