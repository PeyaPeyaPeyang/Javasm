package tokyo.peya.plugin.javasm.compiler.instructions.xload;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatingContext;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDLoadN extends AbstractInstructionEvaluator<JALParser.JvmInsDloadNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull EvaluatingContext evalContxt, JALParser.@NotNull JvmInsDloadNContext ctxt)
    {
        if (has(ctxt.INSN_DLOAD_0()))
            return visitSingle(EOpcodes.DLOAD_0);
        else if (has(ctxt.INSN_DLOAD_1()))
            return visitSingle(EOpcodes.DLOAD_1);
        else if (has(ctxt.INSN_DLOAD_2()))
            return visitSingle(EOpcodes.DLOAD_2);
        else if (has(ctxt.INSN_DLOAD_3()))
            return visitSingle(EOpcodes.DLOAD_3);

        throw new IllegalStateException("Unexpected instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.@NotNull JvmInsDloadNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDloadN();
    }
}
