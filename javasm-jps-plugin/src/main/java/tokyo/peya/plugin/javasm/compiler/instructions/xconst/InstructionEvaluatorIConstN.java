package tokyo.peya.plugin.javasm.compiler.instructions.xconst;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIConstN extends AbstractInstructionEvaluator<JALParser.JvmInsIconstNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator, JALParser.@NotNull JvmInsIconstNContext ctxt)
    {
        if (has(ctxt.INSN_ICONST_M1()))
            return visitSingle(EOpcodes.ICONST_M1);
        else if (has(ctxt.INSN_ICONST_0()))
             return visitSingle(EOpcodes.ICONST_0);
        else if (has(ctxt.INSN_ICONST_1()))
             return visitSingle(EOpcodes.ICONST_1);
        else if (has(ctxt.INSN_ICONST_2()))
             return visitSingle(EOpcodes.ICONST_2);
        else if (has(ctxt.INSN_ICONST_3()))
             return visitSingle(EOpcodes.ICONST_3);
        else if (has(ctxt.INSN_ICONST_4()))
             return visitSingle(EOpcodes.ICONST_4);
        else if (has(ctxt.INSN_ICONST_5()))
            return visitSingle(EOpcodes.ICONST_5);

        throw new IllegalArgumentException("Unknown instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.JvmInsIconstNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIconstN();
    }
}
