package tokyo.peya.javasm.langjal.compiler.instructions.ifx;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.JumpInsnNode;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.LabelInfo;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIfOP extends AbstractInstructionEvaluator<JALParser.JvmInsIfOPContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsIfOPContext ctxt)
    {
        int opcode = getOpcode(ctxt);
        JALParser.LabelNameContext labelNameContext = ctxt.labelName();
        LabelInfo label = evaluator.resolveLabel(labelNameContext.getText());

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

        throw new IllegalArgumentException("Unknown IF opcode");
    }
}
