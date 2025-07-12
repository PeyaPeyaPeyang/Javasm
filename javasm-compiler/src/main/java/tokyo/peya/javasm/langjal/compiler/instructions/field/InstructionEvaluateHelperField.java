package tokyo.peya.javasm.langjal.compiler.instructions.field;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.FieldInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;

public class InstructionEvaluateHelperField
{
    @NotNull
    public static EvaluatedInstruction evaluate(JALParser.JvmInsArgFieldRefContext ref, int opcode)
    {
        JALParser.JvmInsArgFieldRefTypeContext fieldOwner = ref.jvmInsArgFieldRefType();
        JALParser.JvmInsArgFieldRefNameContext fieldName = ref.jvmInsArgFieldRefName();
        JALParser.TypeDescriptorContext fieldType = ref.typeDescriptor();

        FieldInsnNode fieldInsn = new FieldInsnNode(
                opcode,
                fieldOwner.getText(),
                fieldName.getText(),
                fieldType.getText()
        );
        return EvaluatedInstruction.of(fieldInsn);
    }
}
