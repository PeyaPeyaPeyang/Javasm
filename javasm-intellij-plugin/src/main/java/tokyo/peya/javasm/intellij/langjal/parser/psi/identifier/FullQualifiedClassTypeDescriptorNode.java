package tokyo.peya.javasm.intellij.langjal.parser.psi.identifier;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FullQualifiedClassTypeDescriptorNode extends FullQualifiedNameNode
{
    public FullQualifiedClassTypeDescriptorNode(IElementType type, CharSequence text)
    {
        super(type, text);
    }

    @Nullable
    public String getDotName()
    {
        // L..; という形式で表されるクラスの型記述子から，ドット区切りの完全修飾クラス名を取得する
        String text = this.getText();
        if (text.startsWith("L") && text.endsWith(";"))
        {
            String internalName = text.substring(1, text.length() - 1); // Lと;を取り除く
            return internalName.replace('/', '.'); // スラッシュをドットに置換して完全修飾クラス名を返す
        }
        else
            throw new IllegalStateException("Invalid class type descriptor format: " + text);
    }
}
