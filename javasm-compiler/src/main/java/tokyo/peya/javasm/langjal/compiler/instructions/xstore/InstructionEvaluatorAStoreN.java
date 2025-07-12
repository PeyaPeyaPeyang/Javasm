package tokyo.peya.javasm.langjal.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.VarInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementCapsule;
import tokyo.peya.javasm.langjal.compiler.exceptions.IllegalInstructionException;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorAStoreN extends AbstractInstructionEvaluator<JALParser.JvmInsAstoreNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsAstoreNContext ctxt)
    {
        JALParser.LocalInstigationContext ins = ctxt.localInstigation();
        if (has(ctxt.INSN_ASTORE_0()))
            return InstructionEvaluateHelperXStore.evaluateN(this, EOpcodes.ASTORE, 0, compiler, "I", ins);
        else if (has(ctxt.INSN_ASTORE_1()))
            return InstructionEvaluateHelperXStore.evaluateN(this, EOpcodes.ASTORE, 1, compiler, "I", ins);
        else if (has(ctxt.INSN_ASTORE_2()))
            return InstructionEvaluateHelperXStore.evaluateN(this, EOpcodes.ASTORE, 2, compiler, "I", ins);
        else if (has(ctxt.INSN_ASTORE_3()))
            return InstructionEvaluateHelperXStore.evaluateN(this, EOpcodes.ASTORE, 3, compiler, "I", ins);

        throw new IllegalInstructionException("Unexpected instruction: " + ctxt.getText(), ctxt);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        VarInsnNode varInsnNode = (VarInsnNode) instruction.insn();

        StackElementCapsule elementCapsule = new StackElementCapsule(instruction);
        return FrameDifferenceInfo.builder(instruction)
                                  .popToCapsule(elementCapsule)
                                  .addLocalFromCapsule(varInsnNode.var, elementCapsule)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsAstoreNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAstoreN();
    }
}
