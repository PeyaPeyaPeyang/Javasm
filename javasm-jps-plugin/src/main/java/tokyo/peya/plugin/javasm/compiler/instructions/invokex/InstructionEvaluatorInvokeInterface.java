package tokyo.peya.plugin.javasm.compiler.instructions.invokex;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorInvokeInterface extends AbstractInstructionEvaluator<JALParser.JvmInsInvokeinterfaceContext>
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
