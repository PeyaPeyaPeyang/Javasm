package tokyo.peya.javasm.langjal.compiler.instructions.invokex;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InvokeDynamicInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.ClassReferenceType;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.jvm.MethodDescriptor;
import tokyo.peya.javasm.langjal.compiler.jvm.PrimitiveTypes;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluateHelperInvocation
{
    @NotNull
    public static EvaluatedInstruction evaluate(@NotNull AbstractInstructionEvaluator<?> evaluator,
                                                @NotNull ClassNode ownerClazz,
                                                @NotNull JALParser.JvmInsArgMethodRefContext ref, int opcode)
    {
        JALParser.JvmInsArgMethodRefOwnerTypeContext methodOwner = ref.jvmInsArgMethodRefOwnerType();
        JALParser.MethodNameContext methodName = ref.methodName();
        JALParser.MethodDescriptorContext methodDescriptor = ref.methodDescriptor();
        return evaluate(
                evaluator,
                methodOwner == null ? ownerClazz.name: methodOwner.getText(),
                methodName.getText(),
                methodDescriptor.getText(),
                opcode
        );
    }

    public static FrameDifferenceInfo getFrameNormalDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        MethodInsnNode method = (MethodInsnNode) instruction.insn();
        MethodDescriptor descriptor = MethodDescriptor.parse(method.desc);
        TypeDescriptor returnType = descriptor.getReturnType();
        TypeDescriptor[] parameterTypes = descriptor.getParameterTypes();

        FrameDifferenceInfo.Builder builder = FrameDifferenceInfo.builder(instruction);
        if (!(method.getOpcode() == EOpcodes.INVOKESTATIC || method.getOpcode() == EOpcodes.INVOKEDYNAMIC))
        {
            // インスタンスメソッドの場合は，所有者クラスのインスタンスをスタックからポップする
            builder.popObjectRef(ClassReferenceType.parse(method.owner));
        }

        return getFrameDifferenceInfo(instruction, returnType, parameterTypes, builder);
    }

    public static FrameDifferenceInfo getFrameInvokedynamicFrameDifference(@NotNull InstructionInfo instruction)
    {
        InvokeDynamicInsnNode method = (InvokeDynamicInsnNode) instruction.insn();
        MethodDescriptor descriptor = MethodDescriptor.parse(method.desc);
        TypeDescriptor returnType = descriptor.getReturnType();
        TypeDescriptor[] parameterTypes = descriptor.getParameterTypes();

        FrameDifferenceInfo.Builder builder = FrameDifferenceInfo.builder(instruction);
        return getFrameDifferenceInfo(instruction, returnType, parameterTypes, builder);
    }

    @NotNull
    private static FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction,
                                                              @NotNull TypeDescriptor returnType,
                                                              @NotNull TypeDescriptor[] parameterTypes,
                                                              @NotNull FrameDifferenceInfo.Builder builder)
    {
        for (int i = parameterTypes.length - 1; i >= 0; i--)   // 逆順
        {
            TypeDescriptor type = parameterTypes[i];
            builder.pop(type.toStackElement(instruction));
        }
        if (returnType.getBaseType() != PrimitiveTypes.VOID)
            builder.push(returnType.toStackElement(instruction));

        return builder.build();
    }

    public static EvaluatedInstruction evaluate(@NotNull AbstractInstructionEvaluator<?> evaluator,
                                                @NotNull String ownerType,
                                                @NotNull String methodName,
                                                @NotNull String methodDescriptor, int opcode)
    {
        MethodInsnNode insn = new MethodInsnNode(
                opcode,
                ownerType,
                methodName,
                methodDescriptor
        );
        return EvaluatedInstruction.of(evaluator, insn);
    }
}
