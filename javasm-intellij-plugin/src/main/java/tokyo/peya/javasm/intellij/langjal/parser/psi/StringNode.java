package tokyo.peya.javasm.intellij.langjal.parser.psi;

import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.psi.ANTLRPsiLeafNode;

public class StringNode extends ANTLRPsiLeafNode
{
    public StringNode(IElementType type, CharSequence text)
    {
        super(type, text);
    }

    public String toString()
    {
        return "String(" + this.getText() + ")";
    }

    public String toStringValue()
    {
        String text = this.getText();
        // 文字列の両端のクォートを取り除く
        if ((text.startsWith("\"") && text.endsWith("\"") || text.startsWith("'") && text.endsWith("'")))
            text = text.substring(1, text.length() - 1);

        // エスケープシーケンスを処理
        return text.replace("\\\"", "\"").replace("\\'", "'");
    }
}
