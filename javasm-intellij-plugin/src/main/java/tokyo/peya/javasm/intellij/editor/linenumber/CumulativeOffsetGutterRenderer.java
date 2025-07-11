package tokyo.peya.javasm.intellij.editor.linenumber;

import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;

public class CumulativeOffsetGutterRenderer extends LineMarkerInfo.LineMarkerGutterIconRenderer<PsiElement>
{
    private final int cumulativeOffset;
    private final Icon icon;

    public CumulativeOffsetGutterRenderer(@NotNull LineMarkerInfo<PsiElement> info, @NotNull Editor editor,
                                          int cumulativeOffset)
    {
        super(info);

        this.cumulativeOffset = cumulativeOffset;
        this.icon = new TextIcon(editor, " " + cumulativeOffset);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (!(obj instanceof CumulativeOffsetGutterRenderer that))
            return false;
        return this.cumulativeOffset == that.cumulativeOffset;
    }

    @Override
    public int hashCode()
    {
        return Integer.hashCode(this.cumulativeOffset);
    }

    @Override
    public @NotNull Icon getIcon()
    {
        return this.icon;
    }


}
