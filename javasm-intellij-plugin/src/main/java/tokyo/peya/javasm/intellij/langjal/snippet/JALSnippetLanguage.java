package tokyo.peya.javasm.intellij.langjal.snippet;

import com.intellij.lang.Language;
import org.jetbrains.annotations.NotNull;

public final class JALSnippetLanguage extends Language {
    public static final JALSnippetLanguage INSTANCE = new JALSnippetLanguage();

    private JALSnippetLanguage() {
        super("JAL Snippet");
    }

    @Override
    public boolean isCaseSensitive() {
        return true;
    }

    @Override
    public @NotNull String getDisplayName() {
        return "JAL";
    }
}
