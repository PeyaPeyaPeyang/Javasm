package tokyo.peya.javasm.langjal.compiler.instructions.xcmp_op;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorFCmpOp extends AbstractInstructionEvaluator<JALParser.JvmInsFcmpOPContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsFcmpOPContext ctxt)
    {
        if (has(ctxt.INSN_FCMPG()))
            return visitSingle(EOpcodes.FCMPG);
        else if (has(ctxt.INSN_FCMPL()))
            return visitSingle(EOpcodes.FCMPL);

        throw new IllegalArgumentException("Unknown instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.JvmInsFcmpOPContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFcmpOP();
    }
}
