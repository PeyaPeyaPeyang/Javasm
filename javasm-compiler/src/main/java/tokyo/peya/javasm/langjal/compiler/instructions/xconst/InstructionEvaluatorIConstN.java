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

public class InstructionEvaluatorIConstN extends AbstractInstructionEvaluator<JALParser.JvmInsIconstNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsIconstNContext ctxt)
    {
        if (has(ctxt.INSN_ICONST_M1()))
            return this.visitSingle(ctxt, EOpcodes.ICONST_M1);
        else if (has(ctxt.INSN_ICONST_0()))
            return this.visitSingle(ctxt, EOpcodes.ICONST_0);
        else if (has(ctxt.INSN_ICONST_1()))
            return this.visitSingle(ctxt, EOpcodes.ICONST_1);
        else if (has(ctxt.INSN_ICONST_2()))
            return this.visitSingle(ctxt, EOpcodes.ICONST_2);
        else if (has(ctxt.INSN_ICONST_3()))
            return this.visitSingle(ctxt, EOpcodes.ICONST_3);
        else if (has(ctxt.INSN_ICONST_4()))
            return this.visitSingle(ctxt, EOpcodes.ICONST_4);
        else if (has(ctxt.INSN_ICONST_5()))
            return this.visitSingle(ctxt, EOpcodes.ICONST_5);

        throw new IllegalInstructionException("Unknown instruction: " + ctxt.getText(), ctxt);
    }

    @Override
    public FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .pushPrimitive(StackElementType.INTEGER)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsIconstNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIconstN();
    }
}
