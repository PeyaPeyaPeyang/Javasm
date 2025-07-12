package tokyo.peya.javasm.langjal.compiler.instructions.ldc;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorLDCW extends AbstractInstructionEvaluator<JALParser.JvmInsLdcWContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsLdcWContext ctxt)
    {
        return InstructionEvaluationHelperLDC.evaluate(
                this, ctxt.jvmInsArgScalarType(), InstructionEvaluationHelperLDC.LDC_W
        );
    }

    @Override
    protected FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return InstructionEvaluationHelperLDC.getFrameDifferenceInfo(instruction);
    }

    @Override
    protected JALParser.JvmInsLdcWContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLdcW();
    }
}
