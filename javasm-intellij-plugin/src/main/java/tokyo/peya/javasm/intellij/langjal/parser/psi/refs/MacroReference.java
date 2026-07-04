package tokyo.peya.javasm.intellij.langjal.parser.psi.refs;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReferenceBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.preprocessor.JALPreprocessorDirectiveUtil;

public class MacroReference extends PsiReferenceBase<PsiElement> {
    public MacroReference(@NotNull PsiElement element) {
        super(element, TextRange.from(0, element.getTextLength()));
    }

    public static @Nullable MacroReference createIfMacro(@NotNull PsiElement element) {
        PsiFile file = element.getContainingFile();
        if (file == null)
            return null;

        JALPreprocessorDirectiveUtil.DefineDirective directive = JALPreprocessorDirectiveUtil.findActiveDefineDirectiveAt(
                file.getText(),
                element.getTextRange().getStartOffset(),
                element.getText()
        );
        return directive == null ? null : new MacroReference(element);
    }

    @Override
    public @Nullable PsiElement resolve() {
        PsiFile file = this.getElement().getContainingFile();
        if (file == null)
            return null;

        JALPreprocessorDirectiveUtil.DefineDirective directive = JALPreprocessorDirectiveUtil.findActiveDefineDirectiveAt(
                file.getText(),
                this.getElement().getTextRange().getStartOffset(),
                this.getElement().getText()
        );
        if (directive == null || directive.macroNameRange() == null)
            return null;

        return file.findElementAt(directive.macroNameRange().getStartOffset());
    }
}
