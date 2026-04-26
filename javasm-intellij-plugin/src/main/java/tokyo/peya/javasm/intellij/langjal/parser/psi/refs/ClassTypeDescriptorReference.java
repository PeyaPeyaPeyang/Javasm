package tokyo.peya.javasm.intellij.langjal.parser.psi.refs;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierNode;

public class ClassTypeDescriptorReference extends ClassReference {
    public ClassTypeDescriptorReference(
            @NotNull IdentifierNode element) {
        super(element);
    }

    protected String getQualifiedName(IdentifierNode node) {
        String name = super.getQualifiedName(node);
        // name は L...; という形であるから，消す。
        if (!name.startsWith("L") || !name.endsWith(";")) {
            throw new IllegalStateException("Invalid class type descriptor: " + name);
        }

        return name.substring(1, name.length() - 1);
    }
}
