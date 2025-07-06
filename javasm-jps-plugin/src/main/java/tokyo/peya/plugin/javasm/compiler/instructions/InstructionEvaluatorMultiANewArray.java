package tokyo.peya.plugin.javasm.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.MultiANewArrayInsnNode;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.EvaluatorCommons;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorMultiANewArray
        extends AbstractInstructionEvaluator<JALParser.JvmInsMultianewarrayContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsMultianewarrayContext ctxt)
    {
        JALParser.TypeDescriptorContext typeDescriptor = ctxt.typeDescriptor();
        // Ljava/lang/String; -> java.lang.String に変換
        String typeName = EvaluatorCommons.unwrapClassTypeDescriptor(typeDescriptor.getText());
        int dimensions = EvaluatorCommons.asInteger(ctxt.NUMBER());

        MultiANewArrayInsnNode insn = new MultiANewArrayInsnNode(typeName, dimensions);
        return EvaluatedInstruction.of(insn);
    }

    @Override
    protected JALParser.JvmInsMultianewarrayContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsMultianewarray();
    }
}
