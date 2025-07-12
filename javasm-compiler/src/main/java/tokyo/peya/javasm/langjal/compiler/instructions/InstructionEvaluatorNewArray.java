package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.TypeInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.exceptions.IllegalInstructionException;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorNewArray extends AbstractInstructionEvaluator<JALParser.JvmInsNewarrayContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsNewarrayContext ctxt)
    {
        JALParser.TypeDescriptorContext typeDescriptor = ctxt.typeDescriptor();
        if (typeDescriptor.getText().startsWith("L") || typeDescriptor.getText().startsWith("["))
            throw new IllegalInstructionException(
                    "Primitive type expected for anewarray, but got " + typeDescriptor.getText(),
                    typeDescriptor
            );

        TypeInsnNode type = new TypeInsnNode(EOpcodes.ANEWARRAY, typeDescriptor.getText());
        return EvaluatedInstruction.of(type);
    }

    @Override
    protected JALParser.JvmInsNewarrayContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsNewarray();
    }
}
