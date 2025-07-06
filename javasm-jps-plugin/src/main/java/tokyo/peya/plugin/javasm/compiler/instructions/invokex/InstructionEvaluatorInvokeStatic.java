package tokyo.peya.plugin.javasm.compiler.instructions.invokex;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.MethodInsnNode;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorInvokeStatic extends AbstractInstructionEvaluator<JALParser.JvmInsInvokevirtualContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsInvokevirtualContext ctxt)
    {
        return InstructionEvaluateHelperInvocation.evaluate(ctxt.jvmInsArgMethodRef(), EOpcodes.INVOKESTATIC);
    }

    @Override
    protected JALParser.JvmInsInvokevirtualContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsInvokevirtual();
    }
}
