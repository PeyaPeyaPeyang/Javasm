package tokyo.peya.javasm.langjal.compiler.instructions.xload;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorALoad extends AbstractInstructionEvaluator<JALParser.JvmInsAloadContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsAloadContext ctxt)
    {
        return InstructionEvaluateHelperXLoad.evaluate(
                evaluator,
                ctxt.jvmInsArgLocalRef(),
                Opcodes.ALOAD,
                "aload",
                ctxt.INSN_WIDE()
        );
    }

    @Override
    protected JALParser.JvmInsAloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAload();
    }
}
