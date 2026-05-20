package tokyo.peya.javasm.intellij.editor.markdown;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.lang.Language;
import org.intellij.plugins.markdown.injection.CodeFenceLanguageProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.snippet.JALSnippetLanguage;

import java.util.List;
import java.util.Locale;

public class JALMarkdownCodeFenceLanguageProvider implements CodeFenceLanguageProvider {
    @Override
    public @Nullable Language getLanguageByInfoString(@NotNull String infoString) {
        return switch (normalizeInfoString(infoString)) {
            case "jal", "javasm", "jvm" -> JALSnippetLanguage.INSTANCE;
            default -> null;
        };
    }

    @Override
    public @NotNull List<LookupElement> getCompletionVariantsForInfoString(
            @NotNull CompletionParameters completionParameters
    ) {
        return List.of(
                LookupElementBuilder.create("jal")
                        .withTypeText("JVM Assembly Language", true)
        );
    }

    private static @NotNull String normalizeInfoString(@NotNull String infoString) {
        // Markdown のコード・フェンスの info string は，
        // スペースで区切られた最初の単語が言語識別子として扱われるため，スペース以降を削除して正規化
        String normalized = infoString.strip().toLowerCase(Locale.ROOT);
        int whitespaceIndex = -1;
        for (int i = 0; i < normalized.length(); i++) {
            if (Character.isWhitespace(normalized.charAt(i))) {
                whitespaceIndex = i;
                break;
            }
        }
        if (whitespaceIndex >= 0)
            normalized = normalized.substring(0, whitespaceIndex);
        return normalized;
    }
}
