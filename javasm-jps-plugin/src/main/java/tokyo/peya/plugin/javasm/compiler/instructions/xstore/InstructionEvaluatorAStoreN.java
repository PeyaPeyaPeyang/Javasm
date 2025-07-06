package tokyo.peya.plugin.javasm.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatingContext;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorAStoreN extends AbstractInstructionEvaluator<JALParser.JvmInsAstoreNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull EvaluatingContext evalContxt, JALParser.@NotNull JvmInsAstoreNContext ctxt)
    {
        if (has(ctxt.INSN_ASTORE_0()))
            return visitSingle(EOpcodes.ASTORE_0);
        else if (has(ctxt.INSN_ASTORE_1()))
            return visitSingle(EOpcodes.ASTORE_1);
        else if (has(ctxt.INSN_ASTORE_2()))
            return visitSingle(EOpcodes.ASTORE_2);
        else if (has(ctxt.INSN_ASTORE_3()))
            return visitSingle(EOpcodes.ASTORE_0);

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.@NotNull JvmInsAstoreNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAstoreN();
    }
}
