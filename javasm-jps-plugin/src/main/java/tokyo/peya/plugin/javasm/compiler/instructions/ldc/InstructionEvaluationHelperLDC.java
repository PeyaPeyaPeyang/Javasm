package tokyo.peya.plugin.javasm.compiler.instructions.ldc;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.LdcInsnNode;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.EvaluatorCommons;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

import java.math.BigDecimal;

public class InstructionEvaluationHelperLDC
{
    public static final int LDC = 0;
    public static final int LDC_W = 1;
    public static final int LDC2_W = 2;

    public static @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                         JALParser.@NotNull JvmInsArgScalarTypeContext scalar,
                                                         int ldcType)

    {
        if (ldcType < LDC || ldcType > LDC2_W)
            throw new IllegalArgumentException("Invalid LDC type: " + ldcType);

        TerminalNode number = scalar.NUMBER();
        TerminalNode string = scalar.STRING();
        if (string != null)
        {
            if (ldcType == LDC2_W)
                throw new IllegalArgumentException("ldc2_w cannot be used with string literals, please use ldc or ldc_w instead.");

            String value = string.getText();
            value = value.substring(1, value.length() - 1); // Remove quotes
            LdcInsnNode ldcInsnNode = new LdcInsnNode(value);
            return new EvaluatedInstruction(ldcInsnNode);
        }

        BigDecimal bg = EvaluatorCommons.parse(number.getText());
        if (bg == null)
            throw new IllegalArgumentException("Invalid number format: " + number.getText());

        NumberType type = classify(bg);
        if (type == NumberType.BIGDECIMAL)
            throw new IllegalArgumentException("The numeric value is too large or has too many decimal places to be represented.");

        if (ldcType == LDC && (type == NumberType.FLOAT || type == NumberType.DOUBLE))
            throw new IllegalArgumentException("ldc cannot be used with float or double literals, please use ldc_w instead.");
        else if (ldcType == LDC2_W && !(type == NumberType.LONG || type == NumberType.DOUBLE))
            throw new IllegalArgumentException("ldc2_w can only be used with long or double literals, please use ldc or ldc_w instead.");

        LdcInsnNode ldcInsnNode;
        switch (type)
        {
            case INTEGER:
                ldcInsnNode = new LdcInsnNode(bg.intValue());
                break;
            case LONG:
                ldcInsnNode = new LdcInsnNode(bg.longValue());
                if (ldcType == LDC)
                    ldcType = LDC_W; // LDC cannot handle long, so we use LDC_W
                break;
            case FLOAT:
                ldcInsnNode = new LdcInsnNode(bg.floatValue());
                break;
            case DOUBLE:
                ldcInsnNode = new LdcInsnNode(bg.doubleValue());
                break;
            default:
                throw new IllegalArgumentException("Unsupported number type: " + type);
        }

        return EvaluatedInstruction.of(ldcInsnNode);
    }



    @SuppressWarnings("UnpredictableBigDecimalConstructorCall")
    private static NumberType classify(BigDecimal value)
    {
        BigDecimal stripped = value.stripTrailingZeros();

        // 整数チェック
        if (stripped.scale() <= 0)
        {
            if (value.compareTo(BigDecimal.valueOf(Integer.MIN_VALUE)) >= 0 &&
                    value.compareTo(BigDecimal.valueOf(Integer.MAX_VALUE)) <= 0)
                return NumberType.INTEGER;
            else if (value.compareTo(BigDecimal.valueOf(Long.MIN_VALUE)) >= 0 &&
                    value.compareTo(BigDecimal.valueOf(Long.MAX_VALUE)) <= 0)
                return NumberType.LONG;
        }

        // float 精度で表せるか
        if (new BigDecimal(value.floatValue()).compareTo(value) == 0)
            return NumberType.FLOAT;
        else if (new BigDecimal(value.doubleValue()).compareTo(value) == 0)  // double 精度で表せるか
            return NumberType.DOUBLE;

        // それ以外は BigDecimal
        return NumberType.BIGDECIMAL;
    }

    private enum NumberType
    {
        INTEGER, LONG, FLOAT, DOUBLE, BIGDECIMAL
    }
}
