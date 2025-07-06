package tokyo.peya.plugin.javasm.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.JumpInsnNode;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.compiler.LabelInfo;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorJsr extends AbstractInstructionEvaluator<JALParser.JvmInsJsrContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsJsrContext ctxt)
    {
        JALParser.LabelNameContext labelNameContext = ctxt.labelName();
        LabelInfo label = evaluator.resolveLabel(labelNameContext.getText());

        JumpInsnNode insn = new JumpInsnNode(EOpcodes.JSR, label.node());
        return EvaluatedInstruction.of(insn);
    }

    @Override
    protected JALParser.@NotNull JvmInsJsrContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsJsr();
    }
}
