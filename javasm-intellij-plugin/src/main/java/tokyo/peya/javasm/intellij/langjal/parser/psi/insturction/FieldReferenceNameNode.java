package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction;

import com.intellij.lang.ASTNode;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FieldReferenceNameNode extends ANTLRPsiNode
{
    public FieldReferenceNameNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @Nullable
    public String getFieldName()
    {
        // The field name is the text of this node
        return this.getText();
    }
}
