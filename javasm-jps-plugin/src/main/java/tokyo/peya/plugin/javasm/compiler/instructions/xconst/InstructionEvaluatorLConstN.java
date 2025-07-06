package tokyo.peya.plugin.javasm.compiler.instructions.xconst;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLConstN extends AbstractInstructionEvaluator<JALParser.JvmInsLconstNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator, JALParser.@NotNull JvmInsLconstNContext ctxt)
    {
        if (has(ctxt.INSN_LCONST_0()))
            return visitSingle(EOpcodes.LCONST_0);
        if (has(ctxt.INSN_LCONST_1()))
            return visitSingle(EOpcodes.LCONST_1);

        throw new IllegalArgumentException("Unknown instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.JvmInsLconstNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLconstN();
    }
}
