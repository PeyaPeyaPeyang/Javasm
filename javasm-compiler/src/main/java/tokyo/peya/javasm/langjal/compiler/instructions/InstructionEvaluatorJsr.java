package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.JumpInsnNode;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.member.LabelInfo;

public class InstructionEvaluatorJsr extends AbstractInstructionEvaluator<JALParser.JvmInsJsrContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsJsrContext ctxt)
    {
        JALParser.LabelNameContext labelNameContext = ctxt.labelName();
        LabelInfo label = compiler.getLabels().resolve(labelNameContext.getText());

        JumpInsnNode insn = new JumpInsnNode(EOpcodes.JSR, label.node());
        return EvaluatedInstruction.of(insn);
    }

    @Override
    protected JALParser.JvmInsJsrContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsJsr();
    }
}
