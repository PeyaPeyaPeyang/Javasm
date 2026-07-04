package tokyo.peya.javasm.intellij.langjal.parser.psi;

import com.intellij.psi.PsiReference;
import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.psi.ANTLRPsiLeafNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.preprocessor.JALPreprocessorDirectiveUtil;
import tokyo.peya.javasm.intellij.langjal.parser.psi.refs.MacroReference;

public class StringNode extends ANTLRPsiLeafNode {
    public StringNode(IElementType type, CharSequence text) {
        super(type, text);
    }

    public String toString() {
        return "String(" + this.getEffectiveText() + ")";
    }

    public String toStringValue() {
        String text = this.getEffectiveText();
        // 文字列の両端のクォートを取り除く
        if ((text.startsWith("\"") && text.endsWith("\"") || text.startsWith("'") && text.endsWith("'")))
            text = text.substring(1, text.length() - 1);

        // エスケープシーケンスを処理
        return text.replace("\\\"", "\"").replace("\\'", "'");
    }

    private @NotNull String getEffectiveText() {
        String text = this.getText();
        String macroValue = JALPreprocessorDirectiveUtil.resolveMacroValue(this);
        return macroValue == null ? text : macroValue;
    }

    @Override
    public PsiReference getReference() {
        return MacroReference.createIfMacro(this);
    }
}
