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

public class InstructionEvaluatorFStore extends AbstractInstructionEvaluator<JALParser.JvmInsFstoreContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsFstoreContext ctxt)
    {
        return InstructionEvaluateHelperXStore.evaluate(
                this,
                EOpcodes.FSTORE,
                compiler,
                ctxt.jvmInsArgLocalRef(),
                ctxt.localInstigation(),
                "F",
                "fstore",
                ctxt.INSN_WIDE()
        );
    }

    @Override
    public FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        VarInsnNode varInsnNode = (VarInsnNode) instruction.insn();
        return FrameDifferenceInfo.builder(instruction)
                                  .popPrimitive(StackElementType.FLOAT)
                                  .addLocalPrimitive(varInsnNode.var, StackElementType.FLOAT)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsFstoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFstore();
    }
}
