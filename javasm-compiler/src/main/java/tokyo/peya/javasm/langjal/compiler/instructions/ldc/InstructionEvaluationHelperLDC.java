package tokyo.peya.javasm.langjal.compiler.instructions.ldc;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.LdcInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.exceptions.IllegalInstructionException;
import tokyo.peya.javasm.langjal.compiler.exceptions.InternalCompileErrorException;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;
import tokyo.peya.javasm.langjal.compiler.utils.EvaluatorCommons;

public class InstructionEvaluationHelperLDC
{
    public static final int LDC = 0;
    public static final int LDC_W = 1;
    public static final int LDC2_W = 2;

    public static @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler evaluator,
                                                         JALParser.@NotNull JvmInsArgScalarTypeContext scalar,
                                                         int ldcType)

    {
        if (ldcType < LDC || ldcType > LDC2_W)
            throw new InternalCompileErrorException("Invalid LDC type: " + ldcType, scalar);

        LdcInsnNode ldcInsnNode;
        TerminalNode number = scalar.NUMBER();
        TerminalNode string = scalar.STRING();
        if (string != null)
        {
            if (ldcType == LDC2_W || ldcType == LDC_W)
                throw new IllegalInstructionException(
                        "ldc2_w cannot be used with string literals, please use ldc or ldc_w instead.",
                        scalar
                );

            String value = string.getText();
            value = value.substring(1, value.length() - 1); // Remove quotes
            ldcInsnNode = new LdcInsnNode(value);
            return EvaluatedInstruction.of(ldcInsnNode);
        }
        else if (number == null)
            throw new IllegalInstructionException(
                    "ldc instruction requires a number or string literal, but found: " + scalar.getText(),
                    scalar
            );

        // assert number != null;

        Number numberValue = EvaluatorCommons.toNumber(number.getText());
        if (numberValue == null)
            throw new IllegalInstructionException("Invalid number value: " + number.getText(), number);

        String numberType = EvaluatorCommons.getNumberType(number.getText());
        boolean isCategory2 = numberType.equals("double") || numberType.equals("long") || numberType.equals("hex-long");
        if (ldcType == LDC2_W && !isCategory2)
            throw new IllegalInstructionException(
                    "ldc2_w can only be used with double or long values, but found: " + numberType, number
            );
        else if (ldcType == LDC && isCategory2)
            throw new IllegalInstructionException(
                    "ldc cannot be used with double or long values, please use ldc2_w instead.",
                    number
            );

        int instructionSize = ldcType == LDC ? 1: (ldcType == LDC_W ? 2: 3);
        ldcInsnNode = new LdcInsnNode(numberValue);
        return EvaluatedInstruction.of(ldcInsnNode, instructionSize);
    }
}
