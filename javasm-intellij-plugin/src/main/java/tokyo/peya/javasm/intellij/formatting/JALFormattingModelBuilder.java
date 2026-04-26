package tokyo.peya.javasm.intellij.formatting;

import com.intellij.formatting.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.JALLanguage;
import tokyo.peya.javasm.intellij.langjal.parser.JALTokens;

public class JALFormattingModelBuilder implements FormattingModelBuilder {

    @Override
    public @NotNull FormattingModel createModel(
            FormattingContext formattingContext
    ) {
        PsiElement element = formattingContext.getPsiElement();
        CodeStyleSettings settings = formattingContext.getCodeStyleSettings();

        // Block を作成（自作クラス）
        JALBlock rootBlock = new JALBlock(
                element.getNode(),
                Wrap.createWrap(WrapType.NONE, false),
                Alignment.createAlignment(),
                createSpacingBuilder(settings)
        );

        return FormattingModelProvider.createFormattingModelForPsiFile(
                element.getContainingFile(), rootBlock, settings
        );
    }

    private SpacingBuilder createSpacingBuilder(CodeStyleSettings settings) {
        CommonCodeStyleSettings jalSettings = settings.getCommonSettings("JAL"); // 言語ID

        return new SpacingBuilder(settings, JALLanguage.INSTANCE)
                .around(JALTokens.LBRACE).spaceIf(jalSettings.SPACE_WITHIN_BRACES)
                .around(JALTokens.RBRACE).spaceIf(jalSettings.SPACE_WITHIN_BRACES)
                .between(JALTokens.LBRACE, JALTokens.RBRACE).spaceIf(jalSettings.SPACE_WITHIN_BRACES);
    }
}
