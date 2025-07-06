package tokyo.peya.plugin.javasm.compiler.instructions.xload;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.VarInsnNode;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.compiler.LocalVariableInfo;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDLoad extends AbstractInstructionEvaluator<JALParser.JvmInsDloadContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator, JALParser.@NotNull JvmInsDloadContext ctxt)
    {
        return InstructionEvaluateHelperXLoad.evaluate(
                evaluator,
                ctxt.jvmInsArgLocalRef(),
                Opcodes.DLOAD,
                "dload",
                ctxt.INSN_WIDE()
        );
    }

    @Override
    protected JALParser.JvmInsDloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDload();
    }
}
