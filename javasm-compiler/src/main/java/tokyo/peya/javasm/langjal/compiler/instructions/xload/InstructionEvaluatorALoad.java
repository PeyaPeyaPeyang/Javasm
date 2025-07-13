package tokyo.peya.javasm.langjal.compiler.instructions.xload;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.VarInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementCapsule;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorALoad extends AbstractInstructionEvaluator<JALParser.JvmInsAloadContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsAloadContext ctxt)
    {
        return InstructionEvaluateHelperXLoad.evaluate(
                this,
                compiler,
                ctxt.jvmInsArgLocalRef(),
                Opcodes.ALOAD,
                "aload",
                ctxt.INSN_WIDE()
        );
    }

    @Override
    public FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        VarInsnNode insn = (VarInsnNode) instruction.insn();
        StackElementCapsule capsule = new StackElementCapsule(instruction);
        return FrameDifferenceInfo.builder(instruction)
                                  .consumeLocal(insn.var, capsule)
                                  .pushFromCapsule(capsule)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsAloadContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAload();
    }
}
