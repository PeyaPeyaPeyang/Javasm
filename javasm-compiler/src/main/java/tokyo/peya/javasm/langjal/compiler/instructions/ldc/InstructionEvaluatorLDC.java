package tokyo.peya.javasm.langjal.compiler.instructions.ldc;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLDC extends AbstractInstructionEvaluator<JALParser.JvmInsLdcContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsLdcContext ctxt)
    {
        return InstructionEvaluationHelperLDC.evaluate(
                evaluator, ctxt.jvmInsArgScalarType(), InstructionEvaluationHelperLDC.LDC
        );
    }

    @Override
    protected JALParser.JvmInsLdcContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLdc();
    }
}
