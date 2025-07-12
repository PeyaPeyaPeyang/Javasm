package tokyo.peya.javasm.langjal.compiler.instructions.field;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.FieldInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.ClassReferenceType;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorGetStatic extends AbstractInstructionEvaluator<JALParser.JvmInsGetstaticContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsGetstaticContext ctxt)
    {
        return InstructionEvaluateHelperField.evaluate(this, ctxt.jvmInsArgFieldRef(), EOpcodes.GETSTATIC);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        FieldInsnNode fieldInsnNode = (FieldInsnNode) instruction.insn();
        return FrameDifferenceInfo.builder(instruction)
                                  .popObjectRef(ClassReferenceType.parse(fieldInsnNode.owner))
                                  .pushObjectRef(ClassReferenceType.parse(fieldInsnNode.desc))
                                  .build();
    }

    @Override
    protected JALParser.JvmInsGetstaticContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsGetstatic();
    }
}
