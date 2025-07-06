package tokyo.peya.plugin.javasm.compiler.instructions.invokex;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorInvokeSpecial
        extends AbstractInstructionEvaluator<JALParser.JvmInsInvokespecialContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsInvokespecialContext ctxt)
    {
        JALParser.JvmInsArgMethodSpecialRefContext ref = ctxt.jvmInsArgMethodSpecialRef();
        String methodName;
        if (ref.KWD_MNAME_CLINIT() != null)
            methodName = "<clinit>";
        else if (ref.KWD_MNAME_INIT() != null)
            methodName = "<init>";
        else
            throw new IllegalArgumentException("Invalid method name for invokespecial: " + ref.getText());

        return InstructionEvaluateHelperInvocation.evaluate(
                ref.jvmInsArgMethodRefOwnerType().getText(),
                methodName,
                ref.methodDescriptor().getText(),
                EOpcodes.INVOKESPECIAL
        );
    }

    @Override
    protected JALParser.JvmInsInvokespecialContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsInvokespecial();
    }
}
