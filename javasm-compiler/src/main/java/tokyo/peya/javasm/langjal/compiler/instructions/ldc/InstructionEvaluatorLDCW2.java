package tokyo.peya.javasm.langjal.compiler.instructions.ldc;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorLDCW2 extends AbstractInstructionEvaluator<JALParser.JvmInsLdc2WContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsLdc2WContext ctxt)
    {
        return InstructionEvaluationHelperLDC.evaluate(
                compiler, ctxt.jvmInsArgScalarType(), InstructionEvaluationHelperLDC.LDC_W
        );
    }

    @Override
    protected JALParser.JvmInsLdc2WContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLdc2W();
    }
}
