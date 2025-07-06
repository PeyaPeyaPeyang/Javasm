package tokyo.peya.plugin.javasm.compiler.instructions.xcmp_op;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDCmpOp extends AbstractInstructionEvaluator<JALParser.JvmInsDcmpOPContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator, JALParser.@NotNull JvmInsDcmpOPContext ctxt)
    {
        if (has(ctxt.INSN_DCMPG()))
            return visitSingle(EOpcodes.DCMPG);
        else if (has(ctxt.INSN_DCMPL()))
            return visitSingle(EOpcodes.DCMPL);

        throw new IllegalArgumentException("Unknown instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.JvmInsDcmpOPContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDcmpOP();
    }
}
