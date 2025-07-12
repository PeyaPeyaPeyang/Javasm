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

public class InstructionEvaluatorIfICmpOP extends AbstractInstructionEvaluator<JALParser.JvmInsIfIcmpOPContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsIfIcmpOPContext ctxt)
    {
        int opcode = getOpcode(ctxt);
        JALParser.LabelNameContext labelNameContext = ctxt.labelName();
        LabelInfo label = compiler.getLabels().resolve(labelNameContext.getText());

        JumpInsnNode insn = new JumpInsnNode(opcode, label.node());
        return EvaluatedInstruction.of(insn);
    }

    @Override
    protected JALParser.JvmInsIfIcmpOPContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIfIcmpOP();
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

        throw new IllegalInstructionException("Unknown IF_ICMP opcode", ctxt);
    }
}
