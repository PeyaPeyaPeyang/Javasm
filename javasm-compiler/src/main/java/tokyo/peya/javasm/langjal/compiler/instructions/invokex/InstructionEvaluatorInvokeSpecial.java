package tokyo.peya.javasm.langjal.compiler.instructions.invokex;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.exceptions.IllegalInstructionException;
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
            throw new IllegalInstructionException(
                    "Invalid method name in invokespecial: " + ref.getText(),
                    ref
            );

        // Owner が指定されていない場合は，命令を持つメソッドのクラスが所有者となる
        JALParser.JvmInsArgMethodRefOwnerTypeContext ownerType = ref.jvmInsArgMethodRefOwnerType();
        String ownerName = ownerType == null ? compiler.getClazz().name: ownerType.getText();


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
