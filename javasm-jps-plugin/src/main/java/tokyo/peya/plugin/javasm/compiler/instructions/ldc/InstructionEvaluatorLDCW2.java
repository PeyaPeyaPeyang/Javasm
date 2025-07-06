package tokyo.peya.plugin.javasm.compiler.instructions.ldc;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLDCW2 extends AbstractInstructionEvaluator<JALParser.JvmInsLdc2WContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsLdc2WContext ctxt)
    {
        return InstructionEvaluationHelperLDC.evaluate(
                evaluator, ctxt.jvmInsArgScalarType(), InstructionEvaluationHelperLDC.LDC_W
        );
    }

    @Override
    protected JALParser.@NotNull JvmInsLdc2WContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLdc2W();
    }
}
