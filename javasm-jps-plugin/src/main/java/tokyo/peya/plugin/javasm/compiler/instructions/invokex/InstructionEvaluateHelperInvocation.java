package tokyo.peya.plugin.javasm.compiler.instructions.invokex;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.EvaluatorCommons;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluateHelperInvocation
{
    @NotNull
    public static EvaluatedInstruction evaluate(JALParser.JvmInsArgMethodRefContext ref, int opcode)
    {
        JALParser.JvmInsArgMethodRefOwnerTypeContext methodOwner = ref.jvmInsArgMethodRefOwnerType();
        JALParser.MethodNameContext methodName = ref.methodName();
        JALParser.MethodDescriptorContext methodDescriptor = ref.methodDescriptor();

        if (opcode == EOpcodes.INVOKESPECIAL)
        {
            String methodNameText = methodName.getText();
            if (!(methodNameText.equals("<init>") || methodNameText.equals("<clinit>")))
                throw new IllegalArgumentException("Method name must be <init> or <clinit> for invokespecial instruction");
        }

        // Ljava/lang/String; -> java/lang/String に変換
        String ownerType = EvaluatorCommons.unwrapClassTypeDescriptor(methodOwner.getText());
        MethodInsnNode insn = new MethodInsnNode(
                opcode,
                ownerType,
                methodName.getText(),
                methodDescriptor.getText()
        );
        return EvaluatedInstruction.of(insn);
    }
}
