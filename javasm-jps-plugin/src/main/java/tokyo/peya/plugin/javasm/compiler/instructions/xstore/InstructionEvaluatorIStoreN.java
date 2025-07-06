package tokyo.peya.plugin.javasm.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatingContext;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIStoreN extends AbstractInstructionEvaluator<JALParser.JvmInsIstoreNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull EvaluatingContext evalContxt, JALParser.@NotNull JvmInsIstoreNContext ctxt)
    {
        if (has(ctxt.INSN_ISTORE_0()))
            return visitSingle(EOpcodes.ISTORE_0);
        else if (has(ctxt.INSN_ISTORE_1()))
            return visitSingle(EOpcodes.ISTORE_1);
        else if (has(ctxt.INSN_ISTORE_2()))
            return visitSingle(EOpcodes.ISTORE_2);
        else if (has(ctxt.INSN_ISTORE_3()))
            return visitSingle(EOpcodes.ISTORE_3);

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.@NotNull JvmInsIstoreNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIstoreN();
    }
}
