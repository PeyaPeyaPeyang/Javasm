package tokyo.peya.javasm.intellij.editor.linenumber;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.colors.EditorColors;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.colors.EditorFontType;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;

public class TextIcon implements Icon
{
    private final String text;
    private final Font font;
    private final Color color;

    public TextIcon(String text, Font font, Color color)
    {
        this.text = text;
        this.font = font;
        this.color = color;
    }

    public TextIcon(@NotNull Editor editor, @NotNull String text)
    {
        Font font = editor.getColorsScheme().getFont(EditorFontType.PLAIN);
        EditorColorsScheme colorsScheme = editor.getColorsScheme();
        Color color = colorsScheme.getColor(EditorColors.LINE_NUMBER_ON_CARET_ROW_COLOR);

        this.text = text;
        this.font = font;
        this.color = color;
    }

    public TextIcon(@NotNull Editor editor, @NotNull String text, Color color)
    {
        Font font = editor.getColorsScheme().getFont(EditorFontType.PLAIN);
        this.text = text;
        this.font = font;
        this.color = color;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        g.setFont(this.font);
        g.setColor(this.color);
        g.drawString(this.text, x, y + g.getFontMetrics().getAscent());
    }

    @Override
    public int getIconWidth()
    {
        return 6 + this.text.length() * 7;
    }

    @Override
    public int getIconHeight()
    {
        return 16;
    }
}
