package tokyo.peya.javasm.langjal.compiler.instructions.xload;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorILoad extends AbstractInstructionEvaluator<JALParser.JvmInsIloadContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsIloadContext ctxt)
    {
        return InstructionEvaluateHelperXLoad.evaluate(
                compiler,
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
