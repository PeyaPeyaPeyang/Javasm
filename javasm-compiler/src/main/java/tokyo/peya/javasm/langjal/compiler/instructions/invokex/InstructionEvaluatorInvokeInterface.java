package tokyo.peya.javasm.langjal.compiler.instructions.invokex;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorInvokeInterface
        extends AbstractInstructionEvaluator<JALParser.JvmInsInvokeinterfaceContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsInvokeinterfaceContext ctxt)
    {
        return InstructionEvaluateHelperInvocation.evaluate(ctxt.jvmInsArgMethodRef(), EOpcodes.INVOKEINTERFACE);
    }

    @Override
    protected JALParser.JvmInsInvokeinterfaceContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsInvokeinterface();
    }
}
