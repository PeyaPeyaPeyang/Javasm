package tokyo.peya.plugin.javasm.compiler.instructions.ifx;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.JumpInsnNode;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.compiler.LabelInfo;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIfACmpOP extends AbstractInstructionEvaluator<JALParser.JvmInsIfAcmpOPContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsIfAcmpOPContext ctxt)
    {
        int opcode = getOpcode(ctxt);
        JALParser.LabelNameContext labelNameContext = ctxt.labelName();
        LabelInfo label = evaluator.resolveLabel(labelNameContext.getText());

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
