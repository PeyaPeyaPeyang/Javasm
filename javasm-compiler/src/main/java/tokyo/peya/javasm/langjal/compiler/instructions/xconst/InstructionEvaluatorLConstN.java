package tokyo.peya.javasm.langjal.compiler.instructions.xconst;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLConstN extends AbstractInstructionEvaluator<JALParser.JvmInsLconstNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsLconstNContext ctxt)
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
