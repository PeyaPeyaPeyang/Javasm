package tokyo.peya.plugin.javasm.compiler.instructions.xconst;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDConstN extends AbstractInstructionEvaluator<JALParser.JvmInsDconstNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator, JALParser.@NotNull JvmInsDconstNContext ctxt)
    {
        if (has(ctxt.INSN_DCONST_0()))
            return visitSingle(EOpcodes.DCONST_0);
        if (has(ctxt.INSN_DCONST_1()))
            return visitSingle(EOpcodes.DCONST_0);

        throw new IllegalArgumentException("Unknown instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.@NotNull JvmInsDconstNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDconstN();
    }
}
