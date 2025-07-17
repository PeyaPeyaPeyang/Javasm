package tokyo.peya.javasm.intellij.stackviewer;

import com.intellij.ui.JBColor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@AllArgsConstructor
public enum StackUIColorPalette
{
    TOP("TOP", new JBColor(0x666666, 0xBBBBBB), new JBColor(0xEEEEEE, 0x333333)),
    INTEGER("Integer", new JBColor(0x004080, 0xBBDDFF), new JBColor(0xCCEEFF, 0x003366)),
    FLOAT("Float", new JBColor(0x005C99, 0xBBE5FF), new JBColor(0xD6ECFF, 0x336699)),
    LONG("Long", new JBColor(0x4A2D7D, 0xBFAAF0), new JBColor(0xD9D0FF, 0x4F3F8A)),
    DOUBLE("Double", new JBColor(0x5C3A92, 0xD2BFFF), new JBColor(0xE1D9FF, 0x5B4FA3)),
    NULL("Null", new JBColor(0x880000, 0xFF9999), new JBColor(0xFFCCCC, 0x662222)),
    UNINITIALIZED_THIS("Uninitialized_this", new JBColor(0x7A5E00, 0xFFD700), new JBColor(0xFFF2B0, 0x554400)),
    OBJECT("Object", new JBColor(0x226622, 0xAADD99), new JBColor(0xD5F5D5, 0x226622)),
    UNINITIALIZED("Uninitialized", new JBColor(0x8A6A00, 0xFFDD88), new JBColor(0xFFF5CC, 0x664400));

    private final String displayName;
    private final JBColor color;
    private final JBColor backgroundColor;

    public StackUIElement toUIElement(@NotNull StackUIElement.DisplayType displayType)
    {
        return new StackUIElement(this.displayName, displayType, this.backgroundColor, this.color);
    }

    public static StackUIElement toUIObjectElement(@NotNull StackUIElement.DisplayType displayType,
                                                   @NotNull String objectName)
    {
        return new StackUIElement(
                "Object: " + objectName,
                displayType,
                OBJECT.backgroundColor,
                OBJECT.color
        );
    }
}
