package tokyo.peya.javasm.langjal.compiler.instructions.invokex;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorInvokeVirtual
        extends AbstractInstructionEvaluator<JALParser.JvmInsInvokevirtualContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsInvokevirtualContext ctxt)
    {
        return InstructionEvaluateHelperInvocation.evaluate(
                this,
                compiler.getClazz(),
                ctxt.jvmInsArgMethodRef(),
                EOpcodes.INVOKEVIRTUAL
        );
    }

    @Override
    public FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return InstructionEvaluateHelperInvocation.getFrameNormalDifferenceInfo(instruction);
    }

    @Override
    protected JALParser.JvmInsInvokevirtualContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsInvokevirtual();
    }
}
