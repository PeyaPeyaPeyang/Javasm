package tokyo.peya.javasm.intellij.stackviewer;

import com.intellij.ui.JBColor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.langjal.analyser.stack.StackElementType;

@Getter
@AllArgsConstructor
public enum StackUIColorPalette
{
    TOP(
            "TOP",
            new JBColor(0x333333, 0xCCCCCC),
            new JBColor(0xEEEEEE, 0x222222)
    ),

    INTEGER(
            "Integer",
            new JBColor(0x003366, 0x99CCFF),
            new JBColor(0xCCE5FF, 0x002244)
    ),

    FLOAT(
            "Float",
            new JBColor(0x007ACC, 0x66BBFF),
            new JBColor(0xD6F0FF, 0x224466)
    ),

    LONG(
            "Long",
            new JBColor(0x3B2F72, 0xB3A0E6),
            new JBColor(0xDDD6FF, 0x3A2F72)
    ),

    DOUBLE(
            "Double",
            new JBColor(0x4A3C8A, 0xC2B3FF),
            new JBColor(0xE2DFFF, 0x4B3D8A)
    ),

    NULL(
            "Null",
            new JBColor(0x990000, 0xFF9999),
            new JBColor(0xFFCCCC, 0x661111)
    ),

    UNINITIALIZED_THIS(
            "Uninitialized_this",
            new JBColor(0x665500, 0xFFDD44),
            new JBColor(0xFFF3B0, 0x554400)
    ),

    OBJECT(
            "Object",
            new JBColor(0x0077CC, 0x99DDEE),
            new JBColor(0xD0F0FF, 0x224466)
    ),
    UNINITIALIZED(
            "Uninitialized",
            new JBColor(0x886600, 0xFFDD88),
            new JBColor(0xFFF5CC, 0x664400)
    );
    private final String displayName;
    private final JBColor color;
    private final JBColor backgroundColor;

    public StackUIElement toUIElement(@NotNull StackUIElement.DisplayType displayType)
    {
        return new StackUIElement(this.displayName, displayType, this.backgroundColor, this.color);
    }

    public static StackUIColorPalette fromStackElementType(@NotNull StackElementType type)
    {
        return switch (type)
        {
            case INTEGER -> INTEGER;
            case FLOAT -> FLOAT;
            case LONG -> LONG;
            case DOUBLE -> DOUBLE;
            case NULL -> NULL;
            case UNINITIALIZED_THIS -> UNINITIALIZED_THIS;
            case OBJECT -> OBJECT;
            case UNINITIALIZED -> UNINITIALIZED;
            default -> TOP; // TOP, NOP, RETURN_ADDRESS
        };
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
