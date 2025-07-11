package tokyo.peya.javasm.langjal.compiler.instructions.invokex;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorInvokeVirtual
        extends AbstractInstructionEvaluator<JALParser.JvmInsInvokevirtualContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsInvokevirtualContext ctxt)
    {
        return InstructionEvaluateHelperInvocation.evaluate(
                evaluator.getClazz(),
                ctxt.jvmInsArgMethodRef(),
                EOpcodes.INVOKEVIRTUAL
        );
    }

    @Override
    protected JALParser.JvmInsInvokevirtualContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsInvokevirtual();
    }
}
