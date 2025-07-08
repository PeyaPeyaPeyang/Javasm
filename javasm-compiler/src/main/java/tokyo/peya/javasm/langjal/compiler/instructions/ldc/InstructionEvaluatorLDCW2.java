package tokyo.peya.javasm.langjal.compiler.instructions.ldc;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;

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
    protected JALParser.JvmInsLdc2WContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLdc2W();
    }
}
