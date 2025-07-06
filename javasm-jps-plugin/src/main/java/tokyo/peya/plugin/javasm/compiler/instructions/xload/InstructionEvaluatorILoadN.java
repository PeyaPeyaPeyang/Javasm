package tokyo.peya.plugin.javasm.compiler.instructions.xload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatingContext;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorILoadN extends AbstractInstructionEvaluator<JALParser.JvmInsIloadNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull EvaluatingContext evalContxt, JALParser.@NotNull JvmInsIloadNContext ctxt)
    {
        if (has(ctxt.INSN_ILOAD_0()))
            return visitSingle(EOpcodes.ILOAD_0);
        if (has(ctxt.INSN_ILOAD_1()))
            return visitSingle(EOpcodes.ILOAD_1);
        if (has(ctxt.INSN_ILOAD_2()))
            return visitSingle(EOpcodes.ILOAD_2);
        if (has(ctxt.INSN_ILOAD_3()))
            return visitSingle(EOpcodes.ILOAD_3);

        throw new IllegalArgumentException("Unknown instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.@NotNull JvmInsIloadNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIloadN();
    }
}
