package tokyo.peya.javasm.langjal.compiler.instructions.ifx;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.JumpInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;
import tokyo.peya.javasm.langjal.compiler.member.LabelInfo;

public class InstructionEvaluatorIfNonNull extends AbstractInstructionEvaluator<JALParser.JvmInsIfNonnullContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsIfNonnullContext ctxt)
    {
        JALParser.LabelNameContext labelNameContext = ctxt.labelName();
        LabelInfo label = compiler.getLabels().resolve(labelNameContext.getText());

        JumpInsnNode insn = new JumpInsnNode(EOpcodes.IFNONNULL, label.node());
        return EvaluatedInstruction.of(insn);
    }

    @Override
    protected JALParser.JvmInsIfNonnullContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIfNonnull();
    }
}
