package tokyo.peya.javasm.langjal.compiler.instructions.ldc;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLDCW extends AbstractInstructionEvaluator<JALParser.JvmInsLdcWContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsLdcWContext ctxt)
    {
        return InstructionEvaluationHelperLDC.evaluate(
                evaluator, ctxt.jvmInsArgScalarType(), InstructionEvaluationHelperLDC.LDC_W
        );
    }

    @Override
    protected JALParser.JvmInsLdcWContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLdcW();
    }
}
