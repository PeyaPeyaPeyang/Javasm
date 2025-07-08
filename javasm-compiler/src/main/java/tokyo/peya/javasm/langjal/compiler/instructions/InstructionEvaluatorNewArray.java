package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.TypeInsnNode;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorNewArray extends AbstractInstructionEvaluator<JALParser.JvmInsNewarrayContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsNewarrayContext ctxt)
    {
        JALParser.TypeDescriptorContext typeDescriptor = ctxt.typeDescriptor();
        if (typeDescriptor.getText().startsWith("L") || typeDescriptor.getText().startsWith("["))
            throw new IllegalArgumentException("Primitive type expected for anewarray, but got " + typeDescriptor.getText());

        TypeInsnNode type = new TypeInsnNode(EOpcodes.ANEWARRAY, typeDescriptor.getText());
        return EvaluatedInstruction.of(type);
    }

    @Override
    protected JALParser.JvmInsNewarrayContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsNewarray();
    }
}
