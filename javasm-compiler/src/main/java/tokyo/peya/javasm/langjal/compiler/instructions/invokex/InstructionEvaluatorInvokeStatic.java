package tokyo.peya.javasm.langjal.compiler.instructions.invokex;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorInvokeStatic extends AbstractInstructionEvaluator<JALParser.JvmInsInvokestaticContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsInvokestaticContext ctxt)
    {
        return InstructionEvaluateHelperInvocation.evaluate(
                this,
                compiler.getClazz(),
                ctxt.jvmInsArgMethodRef(),
                EOpcodes.INVOKESTATIC
        );
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return InstructionEvaluateHelperInvocation.getFrameNormalDifferenceInfo(instruction);
    }

    @Override
    protected JALParser.JvmInsInvokestaticContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsInvokestatic();
    }
}
