package tokyo.peya.javasm.intellij.langjal.parser.psi.refs;

import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierNode;

public class ClassTypeDescriptorReference extends ClassReference {
    public ClassTypeDescriptorReference(
            @NotNull IdentifierNode element) {
        super(element, createReferenceRange(element.getText()));
    }

    protected String getQualifiedName(IdentifierNode node) {
        String name = super.getQualifiedName(node);
        // name は L...; という形であるから，消す。
        if (!name.startsWith("L") || !name.endsWith(";")) {
            throw new IllegalStateException("Invalid class type descriptor: " + name);
        }

        return name.substring(1, name.length() - 1);
    }

    private static TextRange createReferenceRange(@NotNull String text) {
        int start = 0;

        // [[[[[.. をスキップ
        while (start < text.length() && text.charAt(start) == '[')
            start++;

        // L で始まり ; で終わることを確認
        if (start < text.length() && text.charAt(start) == 'L' && text.endsWith(";"))
            return TextRange.create(start + 1, text.length() - 1);

        // 違ったらフォールバックで全部。
        return TextRange.create(0, text.length());
    }
}
