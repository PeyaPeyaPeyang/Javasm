package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.VarInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.exceptions.IllegalInstructionException;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;
import tokyo.peya.javasm.langjal.compiler.member.LocalVariableInfo;

public class InstructionEvaluatorRet extends AbstractInstructionEvaluator<JALParser.JvmInsRetContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsRetContext ctxt)
    {
        LocalVariableInfo local = compiler.getLocals().resolve(ctxt.jvmInsArgLocalRef(), "ret");

        int idx = local.index();
        boolean isWide = ctxt.INSN_WIDE() != null;
        if (idx >= 0xFF && !isWide)
            throw new IllegalInstructionException(
                    String.format(
                    "Local variable index %d is too large for ret instruction. Use wide variant with.",
                    idx
                    ), ctxt.jvmInsArgLocalRef()
            );

        VarInsnNode insn = new VarInsnNode(EOpcodes.RET, local.index());

        int size = isWide ? 4: 2;
        return EvaluatedInstruction.of(this, insn, size);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.same();
    }

    @Override
    protected JALParser.JvmInsRetContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsRet();
    }
}
