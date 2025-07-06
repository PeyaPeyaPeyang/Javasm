package tokyo.peya.plugin.javasm.compiler.instructions.ldc;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

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
