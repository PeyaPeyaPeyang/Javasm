package tokyo.peya.javasm.intellij.langjal.parser.psi;

import com.intellij.lang.ASTNode;
import lombok.Getter;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.jvm.AccessLevel;

@Getter
public class AccessLevelNode extends ANTLRPsiNode
{
    private final AccessLevel accessLevel;

    public AccessLevelNode(@NotNull ASTNode node)
    {
        super(node);

        String text = node.getText();
        this.accessLevel = AccessLevel.fromString(text);
    }
}
