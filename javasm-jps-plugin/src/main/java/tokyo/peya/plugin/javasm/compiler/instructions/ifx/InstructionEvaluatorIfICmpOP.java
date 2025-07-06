package tokyo.peya.plugin.javasm.compiler.instructions.ifx;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.JumpInsnNode;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.compiler.LabelInfo;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIfICmpOP extends AbstractInstructionEvaluator<JALParser.JvmInsIfIcmpOPContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsIfIcmpOPContext ctxt)
    {
        int opcode = getOpcode(ctxt);
        JALParser.LabelNameContext labelNameContext = ctxt.labelName();
        LabelInfo label = evaluator.resolveLabel(labelNameContext.getText());

        JumpInsnNode insn = new JumpInsnNode(opcode, label.node());
        return EvaluatedInstruction.of(insn);
    }

    private static int getOpcode(JALParser.JvmInsIfIcmpOPContext ctxt)
    {
        if (ctxt.INSN_IF_ICMPEQ() != null)
            return EOpcodes.IF_ICMPEQ;
        if (ctxt.INSN_IF_ICMPNE() != null)
            return EOpcodes.IF_ICMPNE;
        if (ctxt.INSN_IF_ICMPLT() != null)
            return EOpcodes.IF_ICMPLT;
        if (ctxt.INSN_IF_ICMPGE() != null)
            return EOpcodes.IF_ICMPGE;
        if (ctxt.INSN_IF_ICMPGT() != null)
            return EOpcodes.IF_ICMPGT;
        if (ctxt.INSN_IF_ICMPLE() != null)
            return EOpcodes.IF_ICMPLE;

        throw new IllegalArgumentException("Unknown IF_ICMP opcode");
    }

    @Override
    protected JALParser.JvmInsIfIcmpOPContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIfIcmpOP();
    }
}
