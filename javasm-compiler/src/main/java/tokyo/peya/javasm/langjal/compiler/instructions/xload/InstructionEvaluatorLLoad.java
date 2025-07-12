package tokyo.peya.javasm.langjal.compiler.instructions.xload;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorLLoad extends AbstractInstructionEvaluator<JALParser.JvmInsLloadContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsLloadContext ctxt)
    {
        return InstructionEvaluateHelperXLoad.evaluate(
                compiler,
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
