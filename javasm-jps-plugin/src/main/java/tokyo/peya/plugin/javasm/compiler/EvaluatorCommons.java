package tokyo.peya.plugin.javasm.compiler;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Opcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

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

    public static int asInt(@NotNull TerminalNode node)
    {
        String text = node.getText();
        if (text == null || text.isEmpty())
            return 0;

        // 0x から始まる or 10 進
        if (text.startsWith("0x"))
        {
            try
            {
                return Integer.parseInt(text.substring(2), 16);
            }
            catch (NumberFormatException e)
            {
                return 0;
            }
        }
        else
        {
            try
            {
                return Integer.parseInt(text);
            }
            catch (NumberFormatException e)
            {
                return 0;
            }
        }
    }
}
