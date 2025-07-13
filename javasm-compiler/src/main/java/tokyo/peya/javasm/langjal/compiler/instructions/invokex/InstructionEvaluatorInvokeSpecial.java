package tokyo.peya.javasm.langjal.compiler.instructions.invokex;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.MethodInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.NopElement;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.ObjectElement;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementCapsule;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.UninitializedThisElement;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.jvm.MethodDescriptor;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorInvokeSpecial
        extends AbstractInstructionEvaluator<JALParser.JvmInsInvokespecialContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsInvokespecialContext ctxt)
    {
        JALParser.JvmInsArgMethodRefContext ref = ctxt.jvmInsArgMethodRef();
        String methodName = ref.methodName().getText();

        // Owner が指定されていない場合は，命令を持つメソッドのクラスが所有者となる
        JALParser.JvmInsArgMethodRefOwnerTypeContext ownerType = ref.jvmInsArgMethodRefOwnerType();
        String ownerName = ownerType == null ? compiler.getClazz().name: ownerType.getText();

        return InstructionEvaluateHelperInvocation.evaluate(
                this,
                ownerName,
                methodName,
                ref.methodDescriptor().getText(),
                EOpcodes.INVOKESPECIAL
        );
    }

    @Override
    public FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        MethodInsnNode method = (MethodInsnNode) instruction.insn();
        MethodDescriptor descriptor = MethodDescriptor.parse(method.desc);
        TypeDescriptor returnType = descriptor.getReturnType();
        TypeDescriptor[] parameterTypes = descriptor.getParameterTypes();

        FrameDifferenceInfo.Builder builder = FrameDifferenceInfo.builder(instruction);
        InstructionEvaluateHelperInvocation.opArguments(
                builder,
                instruction,
                parameterTypes
        );

        StackElementCapsule uninitialisedRefCapsule = new StackElementCapsule(
                instruction, actualElm -> {
            // UninitializedThisElement の場合は，ObjectElement をローカル変数０に入れる
            if (actualElm instanceof UninitializedThisElement)
                return new ObjectElement(
                        instruction,
                        TypeDescriptor.className(method.owner)
                );

            return new NopElement(instruction);
        }
        );
        if (method.name.equals("<init>"))
            builder.popToCapsule(uninitialisedRefCapsule);
        else
            builder.popObjectRef();  // 通常のメソッド呼び出し

        if (method.name.equals("<init>") && method.owner.equals(instruction.ownerClass().superName))
            builder.addLocalFromCapsule(0, uninitialisedRefCapsule);

        return builder.build();
    }

    @Override
    protected JALParser.JvmInsInvokespecialContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsInvokespecial();
    }
}
