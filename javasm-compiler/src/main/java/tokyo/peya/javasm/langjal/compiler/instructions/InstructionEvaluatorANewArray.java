package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.TypeInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;
import tokyo.peya.javasm.langjal.compiler.utils.EvaluatorCommons;

public class InstructionEvaluatorANewArray extends AbstractInstructionEvaluator<JALParser.JvmInsAnewArrayContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsAnewArrayContext ctxt)
    {
        JALParser.TypeDescriptorContext typeDescriptor = ctxt.typeDescriptor();
        if (!typeDescriptor.getText().startsWith("L"))
            throw new IllegalArgumentException("Reference type expected for anewarray, but got " + typeDescriptor.getText());

        // Ljava/lang/String; -> java.lang.String に変換
        String typeName = EvaluatorCommons.unwrapClassTypeDescriptor(typeDescriptor.getText());

        TypeInsnNode type = new TypeInsnNode(EOpcodes.ANEWARRAY, typeName);
        return EvaluatedInstruction.of(type);
    }

    @Override
    protected JALParser.JvmInsAnewArrayContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAnewArray();
    }
}
