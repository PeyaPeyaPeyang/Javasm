package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.TypeInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.exceptions.IllegalInstructionException;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;
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
                    "Primitive type expected for newarray, but got " + typeDescriptor.getText(),
                    typeDescriptor
            );

        TypeInsnNode type = new TypeInsnNode(EOpcodes.NEWARRAY, typeDescriptor.getText());
        return EvaluatedInstruction.of(this, type);
    }

    @Override
    public FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        TypeInsnNode insn = (TypeInsnNode) instruction.insn();
        return FrameDifferenceInfo.builder(instruction)
                                  .popPrimitive(StackElementType.INTEGER) // 配列のサイズを指定する int 型をポップ
                                  .pushObjectRef(TypeDescriptor.parse("[" + insn.desc))
                                  .build();
    }

    @Override
    protected JALParser.JvmInsNewarrayContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsNewarray();
    }
}
