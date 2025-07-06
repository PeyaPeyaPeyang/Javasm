package tokyo.peya.plugin.javasm.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.VarInsnNode;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.compiler.LocalVariableInfo;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

import java.lang.classfile.Opcode;

public class InstructionEvaluatorIStore extends AbstractInstructionEvaluator<JALParser.JvmInsIstoreContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator, JALParser.@NotNull JvmInsIstoreContext ctxt)
    {
        return InstructionEvaluateHelperXStore.evaluate(
                EOpcodes.ISTORE,
                evaluator,
                ctxt.jvmInsArgLocalRef(),
                "I"
        );
    }

    @Override
    protected JALParser.@NotNull JvmInsIstoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIstore();
    }
}
