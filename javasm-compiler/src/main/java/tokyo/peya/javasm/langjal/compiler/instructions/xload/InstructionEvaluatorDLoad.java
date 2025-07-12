package tokyo.peya.javasm.langjal.compiler.instructions.xload;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorDLoad extends AbstractInstructionEvaluator<JALParser.JvmInsDloadContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsDloadContext ctxt)
    {
        return InstructionEvaluateHelperXLoad.evaluate(
                compiler,
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
