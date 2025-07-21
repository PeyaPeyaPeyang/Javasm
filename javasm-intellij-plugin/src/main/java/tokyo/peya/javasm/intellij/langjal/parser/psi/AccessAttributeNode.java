package tokyo.peya.javasm.intellij.langjal.parser.psi;

import com.intellij.lang.ASTNode;
import lombok.Getter;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.langjal.compiler.jvm.AccessAttribute;

@Getter
public class AccessAttributeNode extends ANTLRPsiNode
{
    private final AccessAttribute accessAttribute;

    public AccessAttributeNode(@NotNull ASTNode node)
    {
        super(node);

        String text = node.getText();
        this.accessAttribute = AccessAttribute.fromString(text);
    }
}
