package tokyo.peya.javasm.intellij.editor.linenumber;

import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ui.EmptyIcon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;

public class JALCumulativeOffsetLineMarkerProvider implements LineMarkerProvider
{
    @Override
    public @Nullable LineMarkerInfo<?> getLineMarkerInfo(@NotNull PsiElement element)
    {
        if (!(element instanceof InstructionNameNode))
            return null;

        InstructionNode instr = PsiTreeUtil.getParentOfType(element, InstructionNode.class);
        if (instr == null)
            return null;

        int cumulative;
        try
        {
            cumulative = instr.getStartInstructionOffset();
        }
        catch (IllegalStateException e)
        {
            // コードが不完全だったり構文エラーのときにおきる。
            return null;
        }

        Editor editor = FileEditorManager.getInstance(element.getProject()).getSelectedTextEditor();
        if (editor == null)
            return null;

        String tooltip = "Cumulative Offset: " + cumulative;
        return new LineMarkerInfo<>(
                element,
                element.getTextRange(),
                EmptyIcon.ICON_16,
                x -> tooltip,
                null,
                GutterIconRenderer.Alignment.RIGHT,
                () -> tooltip

        )
        {
            @Override
            public GutterIconRenderer createGutterRenderer()
            {
                return new CumulativeOffsetGutterRenderer(this, editor, cumulative);
            }
        };
    }
}
