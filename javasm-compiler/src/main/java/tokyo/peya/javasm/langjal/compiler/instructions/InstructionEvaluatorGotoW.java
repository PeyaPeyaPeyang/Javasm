package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.JumpInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;
import tokyo.peya.javasm.langjal.compiler.member.LabelInfo;

public class InstructionEvaluatorGotoW extends AbstractInstructionEvaluator<JALParser.JvmInsGotoWContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsGotoWContext ctxt)
    {
        JALParser.LabelNameContext labelNameContext = ctxt.labelName();
        LabelInfo label = compiler.getLabels().resolve(labelNameContext.getText());

        JumpInsnNode insn = new JumpInsnNode(EOpcodes.GOTO_W, label.node());
        return EvaluatedInstruction.of(insn);
    }

    @Override
    protected JALParser.JvmInsGotoWContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsGotoW();
    }
}
