package tokyo.peya.javasm.langjal.compiler.instructions.xconst;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.exceptions.IllegalInstructionException;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorDConstN extends AbstractInstructionEvaluator<JALParser.JvmInsDconstNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsDconstNContext ctxt)
    {
        if (has(ctxt.INSN_DCONST_0()))
            return visitSingle(ctxt, EOpcodes.DCONST_0);
        if (has(ctxt.INSN_DCONST_1()))
            return visitSingle(ctxt, EOpcodes.DCONST_0);

        throw new IllegalInstructionException("Unknown instruction: " + ctxt.getText(), ctxt);
    }

    @Override
    protected JALParser.JvmInsDconstNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDconstN();
    }
}
