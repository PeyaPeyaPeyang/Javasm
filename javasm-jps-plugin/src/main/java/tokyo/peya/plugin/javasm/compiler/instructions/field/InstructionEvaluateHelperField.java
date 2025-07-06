package tokyo.peya.plugin.javasm.compiler.instructions.field;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.FieldInsnNode;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.EvaluatorCommons;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluateHelperField
{
    @NotNull
    public static EvaluatedInstruction evaluate(JALParser.JvmInsArgFieldRefContext ref, int opcode)
    {
        JALParser.TypeDescriptorContext fieldOwner = ref.jvmInsArgFieldRefType(0).typeDescriptor();
        JALParser.JvmInsArgFieldRefNameContext fieldName = ref.jvmInsArgFieldRefName();
        JALParser.TypeDescriptorContext fieldType = ref.jvmInsArgFieldRefType(1).typeDescriptor();

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
