package tokyo.peya.javasm.langjal.compiler.instructions.field;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.FieldInsnNode;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.EvaluatorCommons;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluateHelperField
{
    @NotNull
    public static EvaluatedInstruction evaluate(JALParser.JvmInsArgFieldRefContext ref, int opcode)
    {
        JALParser.JvmInsArgFieldRefTypeContext   fieldOwner = ref.jvmInsArgFieldRefType();
        JALParser.JvmInsArgFieldRefNameContext fieldName = ref.jvmInsArgFieldRefName();
        JALParser.TypeDescriptorContext fieldType = ref.typeDescriptor();

        // Ljava/lang/String; -> java/lang/String に変換
        String ownerType = EvaluatorCommons.unwrapClassTypeDescriptor(fieldOwner.getText());
        FieldInsnNode fieldInsn = new FieldInsnNode(
                opcode,
                ownerType,
                fieldName.getText(),
                fieldType.getText()
        );
        return EvaluatedInstruction.of(fieldInsn);
    }
}
