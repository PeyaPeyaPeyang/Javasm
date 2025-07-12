package tokyo.peya.javasm.langjal.compiler.instructions.xconst;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorFConstN extends AbstractInstructionEvaluator<JALParser.JvmInsFconstNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsFconstNContext ctxt)
    {
        if (has(ctxt.INSN_FCONST_0()))
            return visitSingle(EOpcodes.FCONST_0);
        else if (has(ctxt.INSN_FCONST_1()))
            return visitSingle(EOpcodes.FCONST_1);
        else if (has(ctxt.INSN_FCONST_2()))
            return visitSingle(EOpcodes.FCONST_2);

        throw new IllegalArgumentException("Unknown instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.JvmInsFconstNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFconstN();
    }
}
