package tokyo.peya.javasm.langjal.compiler.instructions.xcmp_op;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.exceptions.IllegalInstructionException;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorDCmpOp extends AbstractInstructionEvaluator<JALParser.JvmInsDcmpOPContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsDcmpOPContext ctxt)
    {
        if (has(ctxt.INSN_DCMPG()))
            return visitSingle(ctxt, EOpcodes.DCMPG);
        else if (has(ctxt.INSN_DCMPL()))
            return visitSingle(ctxt, EOpcodes.DCMPL);

        throw new IllegalInstructionException("Invalid instruction: " + ctxt.getText(), ctxt);
    }

    @Override
    protected JALParser.JvmInsDcmpOPContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDcmpOP();
    }
}
