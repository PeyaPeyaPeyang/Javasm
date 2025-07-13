package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.TypeInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.exceptions.IllegalInstructionException;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorNew extends AbstractInstructionEvaluator<JALParser.JvmInsNewContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsNewContext ctxt)
    {
        JALParser.TypeDescriptorContext typeDescriptor = ctxt.typeDescriptor();
        String typeName = typeDescriptor.getText();
        if (!(typeName.startsWith("L") && typeName.endsWith(";")))
            throw new IllegalInstructionException(
                    "Invalid type descriptor: " + typeName + ", expected a class type descriptor.",
                    typeDescriptor
            );

        TypeInsnNode type = new TypeInsnNode(EOpcodes.NEW, typeName);
        return EvaluatedInstruction.of(this, type);
    }

    @Override
    public FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        TypeInsnNode insn = (TypeInsnNode) instruction.insn();
        return FrameDifferenceInfo.builder(instruction)
                                  .pushObjectRef(TypeDescriptor.className(insn.desc))
                                  .build();
    }

    @Override
    protected JALParser.JvmInsNewContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsNew();
    }
}
