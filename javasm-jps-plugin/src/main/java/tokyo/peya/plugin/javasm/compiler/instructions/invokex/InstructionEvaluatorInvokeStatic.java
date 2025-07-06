package tokyo.peya.plugin.javasm.compiler.instructions.invokex;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorInvokeStatic extends AbstractInstructionEvaluator<JALParser.JvmInsInvokestaticContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsInvokestaticContext ctxt)
    {
        return InstructionEvaluateHelperInvocation.evaluate(ctxt.jvmInsArgMethodRef(), EOpcodes.INVOKESTATIC);
    }

    @Override
    protected JALParser.JvmInsInvokestaticContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsInvokestatic();
    }
}
