package tokyo.peya.javasm.intellij.formatting;

import com.intellij.application.options.CodeStyleAbstractConfigurable;
import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.lang.Language;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.psi.codeStyle.CodeStyleConfigurable;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.JALLanguage;

public class JALCodeStyleSettingsProvider extends CodeStyleSettingsProvider {
    @Override
    public @Nullable CustomCodeStyleSettings createCustomSettings(@NotNull CodeStyleSettings settings) {
        return new JALCodeStyleSettings(settings);
    }

    @Override
    public @NotNull CodeStyleConfigurable createConfigurable(@NotNull CodeStyleSettings settings,
                                                             @NotNull CodeStyleSettings modelSettings) {
        return new CodeStyleAbstractConfigurable(settings, modelSettings, this.getConfigurableDisplayName()) {
            @Override
            protected @NotNull CodeStyleAbstractPanel createPanel(@NotNull CodeStyleSettings settings) {
                return new JALCodeStyleMainPanel(this.getCurrentSettings(), settings);
            }
        };
    }

    @Override
    public @Nullable @NlsContexts.ConfigurableName String getConfigurableDisplayName() {
        return "Java Assembly Language";
    }

    @Override
    public @Nullable Language getLanguage() {
        return JALLanguage.INSTANCE;
    }

    private static class JALCodeStyleMainPanel extends TabbedLanguageCodeStylePanel {
        public JALCodeStyleMainPanel(CodeStyleSettings currentSettings, CodeStyleSettings settings) {
            super(JALLanguage.INSTANCE, currentSettings, settings);
        }

    }
}
