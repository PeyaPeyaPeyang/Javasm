package tokyo.peya.plugin.javasm.compiler.instructions.xload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatingContext;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorFLoadN extends AbstractInstructionEvaluator<JALParser.JvmInsFloadNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull EvaluatingContext evalContxt, JALParser.@NotNull JvmInsFloadNContext ctxt)
    {
        if (has(ctxt.INSN_FLOAD_0()))
            return visitSingle(EOpcodes.FLOAD_0);
        else if (has(ctxt.INSN_FLOAD_1()))
            return visitSingle(EOpcodes.FLOAD_1);
        else if (has(ctxt.INSN_FLOAD_2()))
            return visitSingle(EOpcodes.FLOAD_2);
        else if (has(ctxt.INSN_FLOAD_3()))
            return visitSingle(EOpcodes.FLOAD_3);

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.@NotNull JvmInsFloadNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFloadN();
    }
}
