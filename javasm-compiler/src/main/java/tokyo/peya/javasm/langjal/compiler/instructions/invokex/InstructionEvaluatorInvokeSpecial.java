package tokyo.peya.javasm.langjal.compiler.instructions.invokex;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorInvokeSpecial
        extends AbstractInstructionEvaluator<JALParser.JvmInsInvokespecialContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
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

        String ownerName;
        JALParser.JvmInsArgMethodRefOwnerTypeContext ownerType = ref.jvmInsArgMethodRefOwnerType();
        ownerName = ownerType == null ? compiler.getClazz().name: ownerType.getText();

        return InstructionEvaluateHelperInvocation.evaluate(
                ownerName,
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
