package tokyo.peya.plugin.javasm.compiler.instructions.xload;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.VarInsnNode;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.compiler.LocalVariableInfo;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorILoad extends AbstractInstructionEvaluator<JALParser.JvmInsIloadContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator, JALParser.@NotNull JvmInsIloadContext ctxt)
    {
        return InstructionEvaluateHelperXLoad.evaluate(
                evaluator,
                ctxt.jvmInsArgLocalRef(),
                Opcodes.ILOAD,
                "iload",
                ctxt.INSN_WIDE()
        );
    }

    @Override
    protected JALParser.JvmInsIloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIload();
    }
}
