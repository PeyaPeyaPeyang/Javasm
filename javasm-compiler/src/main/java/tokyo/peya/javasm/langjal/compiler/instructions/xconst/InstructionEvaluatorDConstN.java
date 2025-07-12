package tokyo.peya.javasm.langjal.compiler.instructions.xconst;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.exceptions.IllegalInstructionException;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorDConstN extends AbstractInstructionEvaluator<JALParser.JvmInsDconstNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsDconstNContext ctxt)
    {
        if (has(ctxt.INSN_DCONST_0()))
            return this.visitSingle(ctxt, EOpcodes.DCONST_0);
        if (has(ctxt.INSN_DCONST_1()))
            return this.visitSingle(ctxt, EOpcodes.DCONST_0);

        throw new IllegalInstructionException("Unknown instruction: " + ctxt.getText(), ctxt);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .pushPrimitive(StackElementType.DOUBLE)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsDconstNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDconstN();
    }
}
