package tokyo.peya.plugin.javasm.compiler.instructions.ifx;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.JumpInsnNode;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.compiler.LabelInfo;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

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
        return new EvaluatedInstruction(insn);
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

    @Override
    protected JALParser.@NotNull JvmInsIfOPContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIfOP();
    }
}
