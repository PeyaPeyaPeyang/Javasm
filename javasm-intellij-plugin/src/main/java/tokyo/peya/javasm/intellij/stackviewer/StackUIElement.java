package tokyo.peya.javasm.intellij.stackviewer;

import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;

public record StackUIElement(
        @NotNull
        String elementType,
        @NotNull
        DisplayType displayType,
        @NotNull
        JBColor backgroundColor,
        @NotNull
        JBColor textColor
)
{
    public enum DisplayType
    {
        PUSH,
        POP,
        UNCHANGING,
    }
}
