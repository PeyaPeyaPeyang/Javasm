package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.MultiANewArrayInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;
import tokyo.peya.javasm.langjal.compiler.utils.EvaluatorCommons;

public class InstructionEvaluatorMultiANewArray
        extends AbstractInstructionEvaluator<JALParser.JvmInsMultianewarrayContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsMultianewarrayContext ctxt)
    {
        JALParser.TypeDescriptorContext typeDescriptor = ctxt.typeDescriptor();
        // Ljava/lang/String; -> java.lang.String に変換
        String typeName = EvaluatorCommons.unwrapClassTypeDescriptor(typeDescriptor);
        int dimensions = EvaluatorCommons.asInteger(ctxt.NUMBER());

        MultiANewArrayInsnNode insn = new MultiANewArrayInsnNode(typeName, dimensions);
        return EvaluatedInstruction.of(this, insn);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        MultiANewArrayInsnNode insn = (MultiANewArrayInsnNode) instruction.insn();

        FrameDifferenceInfo.Builder builder = FrameDifferenceInfo.builder(instruction);
        for (int i = 0; i < insn.dims; i++)
            builder.popPrimitive(StackElementType.INTEGER);

        builder.pushObjectRef(TypeDescriptor.parse(insn.desc));

        return builder.build();
    }

    @Override
    protected JALParser.JvmInsMultianewarrayContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsMultianewarray();
    }
}
