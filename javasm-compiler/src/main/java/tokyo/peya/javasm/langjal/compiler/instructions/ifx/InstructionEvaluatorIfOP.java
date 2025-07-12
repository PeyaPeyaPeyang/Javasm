package tokyo.peya.javasm.langjal.compiler.instructions.ifx;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.JumpInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.exceptions.IllegalInstructionException;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;
import tokyo.peya.javasm.langjal.compiler.member.LabelInfo;

public class InstructionEvaluatorIfOP extends AbstractInstructionEvaluator<JALParser.JvmInsIfOPContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsIfOPContext ctxt)
    {
        int opcode = getOpcode(ctxt);
        JALParser.LabelNameContext labelNameContext = ctxt.labelName();
        LabelInfo label = compiler.getLabels().resolve(labelNameContext.getText());

        JumpInsnNode insn = new JumpInsnNode(opcode, label.node());
        return EvaluatedInstruction.of(insn);
    }

    @Override
    protected JALParser.JvmInsIfOPContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIfOP();
    }

    private static int getOpcode(JALParser.JvmInsIfOPContext ctxt)
    {
        if (ctxt.INSN_IFEQ() != null)
            return EOpcodes.IFEQ;
        if (ctxt.INSN_IFNE() != null)
            return EOpcodes.IFNE;
        if (ctxt.INSN_IFLT() != null)
            return EOpcodes.IFLT;
        if (ctxt.INSN_IFGE() != null)
            return EOpcodes.IFGE;
        if (ctxt.INSN_IFGT() != null)
            return EOpcodes.IFGT;
        if (ctxt.INSN_IFLE() != null)
            return EOpcodes.IFLE;

        throw new IllegalInstructionException("Unknown IF opcode", ctxt);
    }
}
