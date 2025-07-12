package tokyo.peya.javasm.langjal.compiler.instructions.xcmp_op;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.exceptions.IllegalInstructionException;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorFCmpOp extends AbstractInstructionEvaluator<JALParser.JvmInsFcmpOPContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsFcmpOPContext ctxt)
    {
        if (has(ctxt.INSN_FCMPG()))
            return this.visitSingle(ctxt, EOpcodes.FCMPG);
        else if (has(ctxt.INSN_FCMPL()))
            return this.visitSingle(ctxt, EOpcodes.FCMPL);

        throw new IllegalInstructionException("Unknown instruction: " + ctxt.getText(), ctxt);
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popPrimitive(StackElementType.FLOAT)
                                  .popPrimitive(StackElementType.FLOAT)
                                  .pushPrimitive(StackElementType.INTEGER)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsFcmpOPContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFcmpOP();
    }
}
