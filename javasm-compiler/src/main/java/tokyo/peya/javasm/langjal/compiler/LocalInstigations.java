package tokyo.peya.javasm.langjal.compiler;

import lombok.experimental.UtilityClass;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.jetbrains.annotations.Nullable;

@UtilityClass
public class LocalInstigations
{
    @Nullable
    public static String getLocalName(@Nullable JALParser.LocalInstigationContext instigationContext)
    {
        if (instigationContext == null)
            return null;

        TerminalNode id = instigationContext.ID();
        if (id == null)
            return null;

        return id.getText();
    }
}
