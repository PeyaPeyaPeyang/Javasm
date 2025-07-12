package tokyo.peya.javasm.langjal.compiler.instructions.xconst;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.exceptions.IllegalInstructionException;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorLConstN extends AbstractInstructionEvaluator<JALParser.JvmInsLconstNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsLconstNContext ctxt)
    {
        if (has(ctxt.INSN_LCONST_0()))
            return visitSingle(ctxt, EOpcodes.LCONST_0);
        if (has(ctxt.INSN_LCONST_1()))
            return visitSingle(ctxt, EOpcodes.LCONST_1);

        throw new IllegalInstructionException("Unknown instruction: " + ctxt.getText(), ctxt);
    }

    @Override
    protected JALParser.JvmInsLconstNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLconstN();
    }
}
