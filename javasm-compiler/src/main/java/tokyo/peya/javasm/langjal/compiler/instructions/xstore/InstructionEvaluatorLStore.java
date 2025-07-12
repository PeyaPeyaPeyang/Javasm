package tokyo.peya.javasm.langjal.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.VarInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorLStore extends AbstractInstructionEvaluator<JALParser.JvmInsLstoreContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsLstoreContext ctxt)
    {
        return InstructionEvaluateHelperXStore.evaluate(
                this,
                EOpcodes.LSTORE,
                compiler,
                ctxt.jvmInsArgLocalRef(),
                ctxt.localInstigation(),
                "L",
                "lstore",
                ctxt.INSN_WIDE()
        );
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        VarInsnNode varInsnNode = (VarInsnNode) instruction.insn();
        return FrameDifferenceInfo.builder(instruction)
                                  .popPrimitive(StackElementType.LONG)
                                  .addLocalPrimitive(varInsnNode.var, StackElementType.LONG)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsLstoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLstore();
    }
}
