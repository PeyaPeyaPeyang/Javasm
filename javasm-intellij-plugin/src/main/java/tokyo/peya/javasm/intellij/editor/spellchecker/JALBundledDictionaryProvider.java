package tokyo.peya.javasm.intellij.editor.spellchecker;

import com.intellij.spellchecker.BundledDictionaryProvider;
import org.jetbrains.annotations.NotNull;

public final class JALBundledDictionaryProvider implements BundledDictionaryProvider {
    @Override
    public String @NotNull [] getBundledDictionaries() {
        return new String[]{"/dictionaries/jal.dic"};
    }
}
