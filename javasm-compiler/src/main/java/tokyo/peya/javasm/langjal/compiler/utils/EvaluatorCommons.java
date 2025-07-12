package tokyo.peya.javasm.langjal.compiler.utils;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

import java.util.function.Function;

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
        Number number = toNumber(node.getText());
        if (number == null)
            throw new IllegalArgumentException("Invalid integer value: " + node.getText());
        return number.intValue();
    }

    public static Number toNumber(@Nullable String number)
    {
        if (number == null || number.isEmpty())
            return null;

        String type = getNumberType(number);
        Function<String, ? extends Number> parseFunction = getNumberParsingFunction(type);
        if (parseFunction == null)
            throw new IllegalArgumentException("Unsupported number type: " + type);

        if (!type.startsWith("may-"))
        {
            // "may-" で始まらない場合は，接尾辞がついているので，取り除く
            number = number.replaceAll("[fFdDlL]$", "");
        }
        if (type.endsWith("-hex"))
            number = number.substring(2);

        try
        {
            return parseFunction.apply(number);
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("Invalid number format: " + number, e);
        }
    }

    public static boolean isNumber(@Nullable String number)
    {
        try
        {
            toNumber(number);
            return true;
        }
        catch (IllegalArgumentException e)
        {
            return false;
        }
    }

    private static Function<String, ? extends Number> getNumberParsingFunction(@NotNull String type)
    {
        return switch (type)
        {
            case "float" -> Float::parseFloat;
            case "double", "may-double" -> Double::parseDouble;
            case "long" -> Long::parseLong;
            case "long-hex" -> s -> Long.parseLong(s, 16);
            case "int", "may-int" -> Integer::parseInt;
            case "may-int-hex" -> s -> Integer.parseInt(s, 16);
            default -> null; // null を返すことで fallback させる

        };
    }

    public static String getNumberType(String number)
    {
        if (number == null || number.isEmpty())
            return null;

        if (number.startsWith("0x") || number.startsWith("-0x"))
        {
            if (number.endsWith("l") || number.endsWith("L"))
                return "hex-long";
            else
                return "may-int-hex";
        }

        if (number.endsWith("f") || number.endsWith("F"))
            return "float";
        else if (number.endsWith("d") || number.endsWith("D"))
            return "double";
        else if (number.endsWith("l") || number.endsWith("L"))
            return "long";
        else if (number.contains("."))
            return "may-double";
        else
            return "may-int";
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

    public static boolean toBoolean(String value)
    {
        if ("true".equalsIgnoreCase(value) || "1".equals(value))
            return true;
        else if ("false".equalsIgnoreCase(value) || "0".equals(value))
            return false;
        else
            throw new IllegalArgumentException("Invalid boolean value: " + value);
    }
}
