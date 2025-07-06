package tokyo.peya.plugin.javasm.compiler.instructions.xcmp_op;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorFCmpOp extends AbstractInstructionEvaluator<JALParser.JvmInsFcmpgOPContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsFcmpgOPContext ctxt)
    {
        if (has(ctxt.INSN_FCMPG()))
            return visitSingle(EOpcodes.FCMPG);
        else if (has(ctxt.INSN_FCMPL()))
            return visitSingle(EOpcodes.FCMPL);

        throw new IllegalArgumentException("Unknown instruction: " + ctxt.getText());
    }

    @Override
    protected JALParser.JvmInsFcmpgOPContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFcmpgOP();
    }
}
