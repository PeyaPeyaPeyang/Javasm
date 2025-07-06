package tokyo.peya.plugin.javasm.compiler;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

import java.math.BigDecimal;
import java.math.BigInteger;

public class EvaluatorCommons
{
    public static int asAccessLevel(@Nullable JALParser.AccessLevelContext accessLevel)
    {
        if (accessLevel == null)
            return 0;

        if (accessLevel.KWD_ACC_PUBLIC() != null)
            return EOpcodes.ACC_PUBLIC;
        if (accessLevel.KWD_ACC_PRIVATE() != null)
            return EOpcodes.ACC_PRIVATE;
        if (accessLevel.KWD_ACC_PROTECTED() != null)
            return EOpcodes.ACC_PROTECTED;

        throw new IllegalArgumentException("Unknown access level: " + accessLevel.getText());
    }

    public static String asString(@NotNull TerminalNode node)
    {
        String text = node.getText();
        if (text == null || text.isEmpty())
            return null;

        if (text.startsWith("\"") && text.endsWith("\""))
            text = text.substring(1, text.length() - 1);
        else if (text.startsWith("'") && text.endsWith("'"))
            text = text.substring(1, text.length() - 1);

        return text.replace("\\\"", "\"")
                   .replace("\\'", "'")
                   .replace("\\\\", "\\");
    }

    public static int asInteger(@NotNull TerminalNode node)
    {
        BigDecimal value = parse(node.getText());
        if (value.scale() > 0 || value.precision() > 10)
            throw new IllegalArgumentException("Integer literal is too large: " + node.getText());

        return value.intValue();
    }

    public static BigDecimal parse(@Nullable String input)
    {
        if (input == null || input.isEmpty())
            return BigDecimal.ZERO;

        String trimmed = input.trim().toLowerCase();
        try
        {
            // 16進数（BigDecimalは直接対応してないので、一旦BigIntegerで）
            if (trimmed.startsWith("0x"))
            {
                BigInteger bi = new BigInteger(trimmed.substring(2), 16);
                return new BigDecimal(bi);
            }
            // 10進数
            else
                return new BigDecimal(trimmed);
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }

    public static String unwrapClassTypeDescriptor(@NotNull String typeName)
    {
        if (typeName.startsWith("L") && typeName.endsWith(";"))
            return typeName.substring(1, typeName.length() - 1);
        else if (typeName.startsWith("[L") && typeName.endsWith(";"))
            return typeName.substring(2, typeName.length() - 1);
        else
            throw new IllegalArgumentException("Invalid class type descriptor: " + typeName + ", expected a class type descriptor.");
    }
}
