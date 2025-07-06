package tokyo.peya.plugin.javasm.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.TypeInsnNode;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.EvaluatorCommons;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorCheckCast extends AbstractInstructionEvaluator<JALParser.JvmInsCheckcastContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsCheckcastContext ctxt)
    {
        JALParser.TypeDescriptorContext typeDescriptor = ctxt.typeDescriptor();
        // Ljava/lang/String; -> java.lang.String に変換
        String typeName = EvaluatorCommons.unwrapClassTypeDescriptor(typeDescriptor.getText());

        TypeInsnNode type = new TypeInsnNode(EOpcodes.CHECKCAST, typeName);
        return EvaluatedInstruction.of(type);
    }

    @Override
    protected JALParser.@NotNull JvmInsCheckcastContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsCheckcast();
    }
}
