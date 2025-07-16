package tokyo.peya.javasm.intellij.editor.completion;

import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.patterns.PatternCondition;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.util.ProcessingContext;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class JALCompletionCommons
{
    public static InsertHandler<LookupElement> insertAndNewLine()
    {
        return (ctxt, item) -> {
            Editor editor = ctxt.getEditor();
            Document doc = editor.getDocument();
            int offset = editor.getCaretModel().getOffset();
            // 引数が不要な命令の場合は，改行入れてインデント
            doc.insertString(offset, "\n");
            PsiFile file = ctxt.getFile();
            int line = doc.getLineNumber(offset) + 1;
            int lineStart = doc.getLineStartOffset(line);
            // カーソルを次の行の先頭に移動
            editor.getCaretModel().moveToOffset(lineStart);

            // インデントを調整
            PsiDocumentManager.getInstance(ctxt.getProject()).commitDocument(doc);
            CodeStyleManager codeStyleManager = CodeStyleManager.getInstance(ctxt.getProject());
            codeStyleManager.adjustLineIndent(file, lineStart);
        };
    }

    public static PatternCondition<PsiElement> emptyLine()
    {
        return new PatternCondition<>("emptyLine")
        {
            @Override
            public boolean accepts(@NotNull PsiElement element, ProcessingContext context)
            {
                String text = element.getContainingFile().getViewProvider().getDocument().getText();
                int offset = element.getTextOffset();
                int lineNumber = element.getContainingFile()
                                        .getViewProvider()
                                        .getDocument()
                                        .getLineNumber(offset);

                String lineText = getLineString(element, lineNumber, text);
                return lineText.trim().equals("IntellijIdeaRulezzz");
            }

            private static @NotNull String getLineString(@NotNull PsiElement element, int lineNumber, String text)
            {
                int lineStart = element.getContainingFile()
                                       .getViewProvider()
                                       .getDocument()
                                       .getLineStartOffset(lineNumber);
                int lineEnd = element.getContainingFile()
                                     .getViewProvider()
                                     .getDocument()
                                     .getLineEndOffset(lineNumber);

                return text.substring(lineStart, lineEnd);
            }
        };
    }
}
