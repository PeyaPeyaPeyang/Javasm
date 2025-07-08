package tokyo.peya.javasm.langjal.compiler.instructions.xload;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLLoad extends AbstractInstructionEvaluator<JALParser.JvmInsLloadContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsLloadContext ctxt)
    {
        return InstructionEvaluateHelperXLoad.evaluate(
                evaluator,
                ctxt.jvmInsArgLocalRef(),
                Opcodes.LLOAD,
                "lload",
                ctxt.INSN_WIDE()
        );
    }

    @Override
    protected JALParser.JvmInsLloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLload();
    }
}
