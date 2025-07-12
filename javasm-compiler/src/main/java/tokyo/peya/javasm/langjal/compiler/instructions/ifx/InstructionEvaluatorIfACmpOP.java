package tokyo.peya.javasm.langjal.compiler.instructions.ifx;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.JumpInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;
import tokyo.peya.javasm.langjal.compiler.member.LabelInfo;

public class InstructionEvaluatorIfACmpOP extends AbstractInstructionEvaluator<JALParser.JvmInsIfAcmpOPContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsIfAcmpOPContext ctxt)
    {
        int opcode = getOpcode(ctxt);
        JALParser.LabelNameContext labelNameContext = ctxt.labelName();
        LabelInfo label = compiler.getLabels().resolve(labelNameContext.getText());

        JumpInsnNode insn = new JumpInsnNode(opcode, label.node());
        return EvaluatedInstruction.of(insn);
    }

    @Override
    protected JALParser.JvmInsIfAcmpOPContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIfAcmpOP();
    }

    private static int getOpcode(JALParser.JvmInsIfAcmpOPContext ctxt)
    {
        if (ctxt.INSN_IF_ACMPEQ() != null)
            return EOpcodes.IF_ICMPEQ;
        if (ctxt.INSN_IF_ACMPNE() != null)
            return EOpcodes.IF_ICMPNE;

        throw new IllegalArgumentException("Unknown IF_ICMP opcode");
    }
}
