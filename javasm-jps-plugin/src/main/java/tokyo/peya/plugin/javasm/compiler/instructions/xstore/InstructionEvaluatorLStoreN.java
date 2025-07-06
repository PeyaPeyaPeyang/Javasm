package tokyo.peya.plugin.javasm.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatingContext;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLStoreN extends AbstractInstructionEvaluator<JALParser.JvmInsLstoreNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull EvaluatingContext evalContxt, JALParser.@NotNull JvmInsLstoreNContext ctxt)
    {
        if (has(ctxt.INSN_LSTORE_0()))
            return visitSingle(EOpcodes.LSTORE_0);
        else if (has(ctxt.INSN_LSTORE_1()))
            return visitSingle(EOpcodes.LSTORE_1);
        else if (has(ctxt.INSN_LSTORE_2()))
            return visitSingle(EOpcodes.LSTORE_2);
        else if (has(ctxt.INSN_LSTORE_3()))
            return visitSingle(EOpcodes.LSTORE_0);

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.@NotNull JvmInsLstoreNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLstoreN();
    }
}
