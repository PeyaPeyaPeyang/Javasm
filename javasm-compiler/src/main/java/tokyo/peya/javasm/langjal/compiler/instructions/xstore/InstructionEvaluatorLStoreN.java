package tokyo.peya.javasm.langjal.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.VarInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.exceptions.IllegalInstructionException;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorLStoreN extends AbstractInstructionEvaluator<JALParser.JvmInsLstoreNContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsLstoreNContext ctxt)
    {

        JALParser.LocalInstigationContext ins = ctxt.localInstigation();
        if (has(ctxt.INSN_LSTORE_0()))
            return InstructionEvaluateHelperXStore.evaluateN(this, EOpcodes.LSTORE, 0, compiler, "L", ins);
        else if (has(ctxt.INSN_LSTORE_1()))
            return InstructionEvaluateHelperXStore.evaluateN(this, EOpcodes.LSTORE, 1, compiler, "L", ins);
        else if (has(ctxt.INSN_LSTORE_2()))
            return InstructionEvaluateHelperXStore.evaluateN(this, EOpcodes.LSTORE, 2, compiler, "L", ins);
        else if (has(ctxt.INSN_LSTORE_3()))
            return InstructionEvaluateHelperXStore.evaluateN(this, EOpcodes.LSTORE, 3, compiler, "L", ins);

        throw new IllegalInstructionException("Unexpected instruction: " + ctxt.getText(), ctxt);
    }

    @Override
    public FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        VarInsnNode varInsnNode = (VarInsnNode) instruction.insn();
        return FrameDifferenceInfo.builder(instruction)
                                  .popPrimitive(StackElementType.LONG)
                                  .addLocalPrimitive(varInsnNode.var, StackElementType.LONG)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsLstoreNContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLstoreN();
    }
}
