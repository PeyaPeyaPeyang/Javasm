package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.TypeInsnNode;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorNew extends AbstractInstructionEvaluator<JALParser.JvmInsNewContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsNewContext ctxt)
    {
        JALParser.TypeDescriptorContext typeDescriptor = ctxt.typeDescriptor();
        String typeName = typeDescriptor.getText();
        if (!(typeName.startsWith("L") && typeName.endsWith(";")))
            throw new IllegalArgumentException("Invalid type descriptor: " + typeName + ", expected a class type descriptor.");

        TypeInsnNode type = new TypeInsnNode(EOpcodes.NEW, typeName);
        return EvaluatedInstruction.of(type);
    }

    @Override
    protected JALParser.JvmInsNewContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsNew();
    }
}
