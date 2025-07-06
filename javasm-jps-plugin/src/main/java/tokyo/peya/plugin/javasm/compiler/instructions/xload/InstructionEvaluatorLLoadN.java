package tokyo.peya.plugin.javasm.compiler.instructions.xload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatingContext;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLLoadN extends AbstractInstructionEvaluator<JALParser.JvmInsLloadNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull EvaluatingContext evalContxt, JALParser.@NotNull JvmInsLloadNContext ctxt)
    {
        if (has(ctxt.INSN_LLOAD_0()))
            return visitSingle(EOpcodes.LLOAD_0);
        else if (has(ctxt.INSN_LLOAD_1()))
            return visitSingle(EOpcodes.LLOAD_1);
        else if (has(ctxt.INSN_LLOAD_2()))
            return visitSingle(EOpcodes.LLOAD_2);
        else if (has(ctxt.INSN_LLOAD_3()))
            return visitSingle(EOpcodes.LLOAD_3);

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.@NotNull JvmInsLloadNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLloadN();
    }
}
