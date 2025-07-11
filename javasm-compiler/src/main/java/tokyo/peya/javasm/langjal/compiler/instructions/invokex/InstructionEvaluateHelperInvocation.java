package tokyo.peya.javasm.langjal.compiler.instructions.invokex;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluateHelperInvocation
{
    @NotNull
    public static EvaluatedInstruction evaluate(@NotNull ClassNode ownerClazz,
                                                @NotNull JALParser.JvmInsArgMethodRefContext ref, int opcode)
    {
        JALParser.JvmInsArgMethodRefOwnerTypeContext methodOwner = ref.jvmInsArgMethodRefOwnerType();
        JALParser.MethodNameContext methodName = ref.methodName();
        JALParser.MethodDescriptorContext methodDescriptor = ref.methodDescriptor();
        return evaluate(
                methodOwner == null ? ownerClazz.name: methodOwner.getText(),
                methodName.getText(),
                methodDescriptor.getText(),
                opcode
        );
    }

    public static EvaluatedInstruction evaluate(@NotNull String ownerType, String methodName, String methodDescriptor,
                                                int opcode)
    {
        if (opcode == EOpcodes.INVOKESPECIAL)
        {
            if (!(methodName.equals("<init>") || methodName.equals("<clinit>")))
                throw new IllegalArgumentException(
                        "Method name must be <init> or <clinit> for invokespecial instruction");
        }

        MethodInsnNode insn = new MethodInsnNode(
                opcode,
                ownerType,
                methodName,
                methodDescriptor
        );
        return EvaluatedInstruction.of(insn);
    }
}
