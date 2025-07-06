package tokyo.peya.plugin.javasm.compiler.instructions.xload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatingContext;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorALoadN extends AbstractInstructionEvaluator<JALParser.JvmInsAloadNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull EvaluatingContext evalContxt, JALParser.@NotNull JvmInsAloadNContext ctxt)
    {
        if (has(ctxt.INSN_ALOAD_0()))
            return visitSingle(EOpcodes.ALOAD_0);
        else if (has(ctxt.INSN_ALOAD_1()))
            return visitSingle(EOpcodes.ALOAD_1);
        else if (has(ctxt.INSN_ALOAD_2()))
            return visitSingle(EOpcodes.ALOAD_2);
        else if (has(ctxt.INSN_ALOAD_3()))
            return visitSingle(EOpcodes.ALOAD_3);

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.@NotNull JvmInsAloadNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAloadN();
    }
}
