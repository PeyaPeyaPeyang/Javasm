package tokyo.peya.javasm.langjal.compiler.instructions.invokex;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorInvokeStatic extends AbstractInstructionEvaluator<JALParser.JvmInsInvokestaticContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsInvokestaticContext ctxt)
    {
        return InstructionEvaluateHelperInvocation.evaluate(
                evaluator.getClazz(),
                ctxt.jvmInsArgMethodRef(),
                EOpcodes.INVOKESTATIC
        );
    }

    @Override
    protected JALParser.JvmInsInvokestaticContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsInvokestatic();
    }
}
